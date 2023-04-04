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
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        lblItem.text = receiveItem
        txtItem.text = receiveItem
    }
    

    func receiveItem(_ item: String){
        receiveItem = item
    }
    
    @IBAction func btnEdit(_ sender: UIButton) {
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
