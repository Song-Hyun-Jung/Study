//
//  DateViewController.swift
//  Navigation
//
//  Created by CSMAC12 on 2022/06/29.
//

import UIKit

protocol DateEditDelegate{
    func didDatePickerDone(_ controller: DateViewController, message: String)
}

class DateViewController: UIViewController {
    
    var delegate: DateEditDelegate?
    var textWayValue: String = "" //경로
    var curDate: String = ""

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    

    @IBAction func changeDate(_ sender: UIDatePicker) {
        let datePickerView = sender
        
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd HH:mm:ss EEE"
        curDate = formatter.string(from: datePickerView.date)
    
       
    }
    @IBAction func btnDone(_ sender: UIButton) {
        
        if delegate != nil {
            delegate?.didDatePickerDone(self, message: curDate )
        }
        
        _ = navigationController?.popViewController(animated: true)
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
