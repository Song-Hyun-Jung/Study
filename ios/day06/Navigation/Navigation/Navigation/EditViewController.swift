//
//  EditViewController.swift
//  Navigation
//
//  Created by CSMAC12 on 2022/06/29.
//

import UIKit

protocol EditDelegate{
    func didMessageEditDone(_ controller: EditViewController, message: String)
    func didImageOnOffDone(_ controller: EditViewController, isOn: Bool)
    func didImageZoomDone(_ controller: EditViewController, isZoom: Bool, zoomFlag: Int)
}

class EditViewController: UIViewController {
    
    var delegate: EditDelegate?
    
    //경로
    var textWayValue: String = "" //멤버일 때는 초기화 해야하는데
    //텍스트 입력값
    var textMessage: String = ""
    
    var isOn = false
    var isZoom = false
    var zoom = 0
    
    @IBOutlet var lblWay: UILabel!
    @IBOutlet var txMessage: UITextField!
    @IBOutlet var swIsOn: UISwitch!
    @IBOutlet var btnZoom: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        lblWay.text = textWayValue   //지역변수는 초기화 안해도 됨.
        txMessage.text = textMessage
        
        swIsOn.isOn = isOn
        if isZoom == false{
            btnZoom.setTitle("축소", for: .normal)
        }
        else{
            btnZoom.setTitle("확대", for: .normal)
        }
        // Do any additional setup after loading the view.
        zoom = 0
    }
    

    @IBAction func btnDone(_ sender: UIButton) {
        //원래 화면으로 돌아가도록
        _ = navigationController?.popViewController(animated: true)
        
        //내가 갖는 delegate의 메소드 호출
        if delegate != nil {
            delegate?.didMessageEditDone(self, message: txMessage.text!)
            delegate?.didImageOnOffDone(self, isOn: isOn)
            delegate?.didImageZoomDone(self, isZoom: isZoom, zoomFlag: zoom)
        }
    }
    
    @IBAction func swImageOnOff(_ sender: UISwitch) {
        if sender.isOn {
            isOn = true
        }
        else{
            isOn = false
        }
    }
    
    @IBAction func btnImageZoom(_ sender: UIButton) {
        isZoom = !isZoom
        zoom = 1
    }
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
