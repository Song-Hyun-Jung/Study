//
//  ViewController.swift
//  DatePicker
//
//  Created by CSMAC12 on 2022/06/23.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet var lblCurrentTime: UILabel!
    @IBOutlet var lblPickerTime: UILabel!
    
    @IBOutlet var curView: UIView! // 화면 자체를 변수로
    
    let timeSelector: Selector = #selector(ViewController.updateTime)
    let interval = 1.0
    var count = 0
    var flag = 0
    var count2 = 0
    var flag2 = false
    
    //알람시계
    var alarmTime = "알람"
    var tenSec = "10"
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        Timer.scheduledTimer(timeInterval: interval, target: self,
                             selector: timeSelector, userInfo: nil, repeats: true)
    }
    
    @objc func updateTime() {
        /*
         //초단위 카운트
        lblCurrentTime.text = String(count)
        count = count + 1
         */
        
        
        //시계
        let date = NSDate()
        
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd HH:mm:ss EEE"
        lblCurrentTime.text = "현재시간: " + formatter.string(from: date as Date)
        
        formatter.dateFormat = "hh:mm aaa"
        let currentTime = formatter.string(for:date)
        
        //알람 시간이 되면 대화상자 뜨도록
       
        if(alarmTime == currentTime){
            if(flag2 == false){
                let alarmAlert = UIAlertController(title: "알람", message: "알람 시간이 되었습니다.", preferredStyle: UIAlertController.Style.alert)
                let okAction = UIAlertAction(title: "네, 알겠습니다.", style: UIAlertAction.Style.default, handler: {ACTION in self.flag2 = true})
                alarmAlert.addAction(okAction)
                present(alarmAlert, animated: true, completion: nil)
            }
            if(flag2 == true){
                count2 += 1
            }
            if(count2 == 30){ //60
                flag2 = false
                count2 = 0
            }
        }
        /*
         //알람 시간이 되면 배경 색이 붉게 변함
        //알람 비교
        formatter.dateFormat = "hh:mm:ss aaa"
        let currentTime = formatter.string(for:date)
        
        if(alarmTime == currentTime){
            curView.backgroundColor = UIColor.red
            flag = 1
        }
        
        //  10초 뒤에 다시 하얀색으로 돌아오도록
        if(flag == 1){
            count = count + 1
            if(count == 10){
                curView.backgroundColor = UIColor.white
                flag = 0
            }
        }
         */
    }

    @IBAction func changeDatePicker(_ sender: UIDatePicker) {
        let datePickerView = sender
        
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd HH:mm:ss EEE"
        lblPickerTime.text = "선택시간: " + formatter.string(from: datePickerView.date)
        
        //알람 설정
        formatter.dateFormat = "hh:mm aaa"
        alarmTime = formatter.string(from: datePickerView.date)
        
    }
    
}

