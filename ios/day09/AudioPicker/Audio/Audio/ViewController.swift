//
//  ViewController.swift
//  Audio
//
//  Created by CSMAC12 on 2022/07/04.
//

import UIKit
import AVFoundation

class ViewController: UIViewController, AVAudioPlayerDelegate, AVAudioRecorderDelegate, UIPickerViewDelegate, UIPickerViewDataSource {

   
    @IBOutlet var pvProgressPlay: UIProgressView!
    @IBOutlet var lblCurrentTime: UILabel!
    @IBOutlet var lblEndTime: UILabel!
    @IBOutlet var btnPlay: UIButton!
    @IBOutlet var btnPause: UIButton!
    @IBOutlet var btnStop: UIButton!
    @IBOutlet var slVolume: UISlider!
    @IBOutlet var btnRecord: UIButton!
    @IBOutlet var lblRecordTime: UILabel!
    @IBOutlet var swRecord: UISwitch!
    @IBOutlet var pickerMusic: UIPickerView!
    @IBOutlet var musicTitle: UILabel!
    
    var audioPlayer: AVAudioPlayer!
    var audioFile: URL!
    let MAX_VOLUME: Float = 10.0
    var progressTimer: Timer!
    
    
    //picker view 사용
    var musicFileName=["As You Fade Away", "Everything Has a Beginning", "Skylines", "Sicilian_Breeze"]
    let MAX_ARRAY_NUM = 4
    let PICKER_VIEW_COLUMN = 1
    let PICKER_VIEW_HEIGHT:CGFloat = 30
    var musicArray = [URL?]()
    var pickMusic = ""
    
    let timePlayerSelector: Selector = #selector(ViewController.updatePlayTime)
    
    
    //녹음
    var audioRecorder: AVAudioRecorder!
    var isRecordMode = false
    
    let timeRecordSelector: Selector = #selector(ViewController.updateRecordTime)

    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        /*
        //audioFile = Bundle.main.url(forResource: "Sicilian_Breeze", withExtension: "mp3")
         initPlay()
         */
        
        musicTitle.text = musicFileName[0]
        pickMusic = musicFileName[0]
        
        
        swRecord.isOn = false
        selectAudioFile()
        if !isRecordMode {
            initPlay()
            btnRecord.isEnabled = false
            lblRecordTime.isEnabled = false
        }
        else{
            initRecord()
        }
        
        
        for i in 0 ..< MAX_ARRAY_NUM {
            let music = Bundle.main.url(forResource: musicFileName[i], withExtension: "mp3")
            musicArray.append(music)
        }
        
        
        
    }
    
    func initPlay(){
        do{
            audioPlayer = try AVAudioPlayer(contentsOf: audioFile)
        }
        catch let error as NSError{
            print("Error-initPlay: \(error)")
        }
        
        slVolume.maximumValue = MAX_VOLUME
        slVolume.value = 1.0
        pvProgressPlay.progress = 0
        
        audioPlayer.delegate = self
        audioPlayer.prepareToPlay()
        audioPlayer.volume = slVolume.value
        
        lblEndTime.text = convertNSTimeInterval2String(audioPlayer.duration)
        lblCurrentTime.text = convertNSTimeInterval2String(0)
        
        /*
        btnPlay.isEnabled = true
        btnPause.isEnabled = false
        btnStop.isEnabled = false
         */
        setPlayButtons(true, pause: false, stop: false)
        
    }
    
    func convertNSTimeInterval2String(_ time: TimeInterval) -> String{
        let min = Int(time / 60)
        let sec = Int(time.truncatingRemainder(dividingBy: 60))
        let strTime = String(format: "%02d:%02d", min, sec)
        return strTime
    }

    func setPlayButtons(_ play: Bool, pause: Bool, stop: Bool){
        btnPlay.isEnabled = play
        btnPause.isEnabled = pause
        btnStop.isEnabled = stop
    }
    
    @IBAction func btnPlayAudio(_ sender: UIButton) {
        audioPlayer.play()
        setPlayButtons(false, pause: true, stop: true)
        progressTimer = Timer.scheduledTimer(timeInterval: 0.1, target: self, selector: timePlayerSelector, userInfo: nil, repeats: true)
    }
    
    @objc func updatePlayTime(){
        lblCurrentTime.text = convertNSTimeInterval2String(audioPlayer.currentTime)
        pvProgressPlay.progress = Float(audioPlayer.currentTime/audioPlayer.duration)
    }
    
    @IBAction func btnPauseAudio(_ sender: UIButton) {
        audioPlayer.pause()
        setPlayButtons(true, pause: false, stop: true)
    }
    
    @IBAction func btnStopAudio(_ sender: UIButton) {
        audioPlayer.stop()
        audioPlayer.currentTime = 0
        lblCurrentTime.text = convertNSTimeInterval2String(0)
        setPlayButtons(true, pause: false, stop: false)
        progressTimer.invalidate()
    }
    
    @IBAction func slChangeVolume(_ sender: UISlider) {
        audioPlayer.volume = slVolume.value
    }
    
    func audioPlayerDidFinishPlaying(_ player: AVAudioPlayer, successfully flag: Bool) {
        progressTimer.invalidate()
        setPlayButtons(true, pause: false, stop: false)
    }
    
    
    //녹음
    func selectAudioFile(){
        if !isRecordMode {
            audioFile = Bundle.main.url(forResource:pickMusic, withExtension: "mp3")
        }
        else{
            let documentDirectory = FileManager.default.urls(for:.documentDirectory,
                                                             in: .userDomainMask)[0]
            audioFile = documentDirectory.appendingPathComponent("recordFile.m4a")
        }
    }
    
    func initRecord(){
        let recordSettings = [
            AVFormatIDKey: NSNumber(value: kAudioFormatAppleLossless as UInt32),
            AVEncoderAudioQualityKey: AVAudioQuality.max.rawValue,
            AVEncoderBitRateKey: 320000,
            AVNumberOfChannelsKey: 2,
            AVSampleRateKey: 44100.0
        ] as [String: Any]
        
        do{
            audioRecorder = try AVAudioRecorder(url: audioFile, settings: recordSettings)
        }
        catch let error as NSError{
            print("Error-initRecord: \(error)")
        }
                  
        audioRecorder.delegate = self
        
        slVolume.value = 1.0
        audioPlayer.volume = slVolume.value
        lblEndTime.text = convertNSTimeInterval2String(0)
        lblCurrentTime.text = convertNSTimeInterval2String(0)
        setPlayButtons(false, pause: false, stop: false)
        
        let session = AVAudioSession.sharedInstance()
        do{
            try AVAudioSession.sharedInstance().setCategory(.playAndRecord, mode: .default)
            try AVAudioSession.sharedInstance().setActive(true)
        }
        catch let error as NSError{
            print("Error-setCategoty: \(error)")
        }
        
        do{
            try session.setActive(true)
        }
        catch let error as NSError{
            print("Error-setActive: \(error)")
        }
    }
    
    @IBAction func swRecordMode(_ sender: UISwitch) {
        if sender.isOn{
            audioPlayer.stop()
            audioPlayer.currentTime = 0
            lblRecordTime!.text = convertNSTimeInterval2String(0)
            isRecordMode = true
            btnRecord.isEnabled = true
            lblRecordTime.isEnabled = true
        }
        else{
            isRecordMode = false
            btnRecord.isEnabled = false
            lblRecordTime.isEnabled = false
            lblRecordTime.text = convertNSTimeInterval2String(0)
        }
        
        selectAudioFile()
        if !isRecordMode{
            initPlay()
        }
        else{
            initRecord()
        }
    }
    
    
    @IBAction func btnRecord(_ sender: UIButton) {
        if (sender as AnyObject).titleLabel?.text == "Record"{
            audioRecorder.record()
            (sender as AnyObject).setTitle("Stop", for: UIControl.State())
            progressTimer = Timer.scheduledTimer(timeInterval: 0.1, target: self, selector: timeRecordSelector, userInfo: nil, repeats: true)
        }
        else{
            audioRecorder.stop()
            progressTimer.invalidate()
            (sender as AnyObject).setTitle("Record", for: UIControl.State())
            btnPlay.isEnabled = true
            initPlay()
        }
        
    }
    
    @objc func updateRecordTime(){
        lblRecordTime.text = convertNSTimeInterval2String(audioRecorder.currentTime)
    }
    
    
    //picker view
    //returns the number of columns to display
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return PICKER_VIEW_COLUMN
    }
    
    //returns the # of rows in each component
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return musicFileName.count
    }

    
    //스크롤 내용에 글씨가 나오도록
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return musicFileName[row]
    }
    
    

    //룰렛 높이 변경
    func pickerView(_ pickerView: UIPickerView, rowHeightForComponent component: Int) -> CGFloat {
        return PICKER_VIEW_HEIGHT
    }
    
    //선택된 항목 해당 내용 뷰에 출력하기
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int){
        if(component == 0){
            musicTitle.text = musicFileName[row]
            pickMusic = musicFileName[row]
            print(pickMusic)
            selectAudioFile()
            initPlay()
        }
    }
}

