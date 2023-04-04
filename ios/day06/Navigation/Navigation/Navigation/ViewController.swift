//
//  ViewController.swift
//  Navigation
//
//  Created by CSMAC12 on 2022/06/29.
//

import UIKit

class ViewController: UIViewController, EditDelegate, DateEditDelegate {
    
    @IBOutlet var txMessage: UITextField!
    @IBOutlet var imgView: UIImageView!
    @IBOutlet var lblCurrentTime: UILabel!
    
    let imgOn = UIImage(named: "lamp_on.png")
    let imgOff = UIImage(named: "lamp_off.png")
    
    var isOn = true
    var isZoom = true
    
    let date = NSDate()
    
    let formatter = DateFormatter()
    var curDate: String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        imgView.image = imgOn
       
        formatter.dateFormat = "yyyy-MM-dd HH:mm:ss EEE"
        lblCurrentTime.text = "현재시간: " + formatter.string(from: date as Date)
        curDate = lblCurrentTime.text!
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.destination is EditViewController {
            //경로 값 다음화면에 보내기
            let editViewController = segue.destination as!
            EditViewController
            
            if segue.identifier == "editButton" {
                editViewController.textWayValue = "segue : use button"
            }
            else if segue.identifier == "editBarButton"{
                editViewController.textWayValue = "segue : use Bar button"
            }
            
            //텍스트 입력값 다음화면에 보내기
            editViewController.textMessage = txMessage.text!
            
            editViewController.isOn = isOn //전구 킴, 끔 보내기
            
            editViewController.isZoom = isZoom
            
            //delegate 역할 할 사람 정함 --> delegate 역할 할 사람이 나다.
            editViewController.delegate = self
        }
        
        else if segue.destination is DateViewController{
            //날짜
            let editDateViewController = segue.destination as! DateViewController
            if segue.identifier == "editDate"{
                editDateViewController.textWayValue = "segue: use editDate Button"
            }
            editDateViewController.curDate = curDate
            editDateViewController.delegate = self
        }
        
    }
    
    
    func didMessageEditDone(_ controller: EditViewController, message: String) {
        txMessage.text = message
    }
    
    func didImageOnOffDone(_ controller: EditViewController, isOn: Bool) {
        if isOn{
            imgView.image = imgOn
            self.isOn = true
        }
        else{
            imgView.image = imgOff
            self.isOn =  false
        }
    }
    
    func didImageZoomDone(_ controller: EditViewController, isZoom: Bool, zoomFlag: Int) {
    
        let scale: CGFloat = 2.0
        var newWidth: CGFloat = 1, newHeight: CGFloat = 1
          
          if(isZoom && zoomFlag == 1) { //true
              newWidth = imgView.frame.width / scale
              newHeight = imgView.frame.height / scale
              
              self.isZoom = true
          }
          else if (!isZoom && zoomFlag == 1){ //false
              newWidth = imgView.frame.width * scale
              newHeight = imgView.frame.height * scale
              
              self.isZoom = false
          }
        else if(zoomFlag == 0){
            newWidth = imgView.frame.width
            newHeight = imgView.frame.height
        }
          
          imgView.frame.size = CGSize(width: newWidth, height: newHeight)
          
    }
    
    func didDatePickerDone(_ controller: DateViewController, message: String) {
        lblCurrentTime.text = message
    }

}

