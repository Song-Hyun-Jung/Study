//
//  DetailViewController.swift
//  Table
//
//  Created by CSMAC12 on 2022/06/30.
//

import UIKit

class DetailViewController: UIViewController {

    @IBOutlet var txtItem: UITextField!
    @IBOutlet var lblItem: UILabel!
    
    
    var receiveItem = ""
    var receiveRow: Int = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        lblItem.text = receiveItem
        txtItem.text = receiveItem
    }
    

    func receiveItem(_ item: String){
        receiveItem = item
    }
    
    func receiveRow(_ r: Int){
        receiveRow = r
    }
    
    @IBAction func btnEdit(_ sender: UIButton) {
        items[receiveRow] = txtItem.text!
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
