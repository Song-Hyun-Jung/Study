//
//  ViewController.swift
//  HelloWorld
//
//  Created by CSMAC12 on 2022/06/22.
//

import UIKit

class ViewController: UIViewController {

    
    @IBOutlet var txtName: UITextField!
    @IBOutlet var lblHello: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    @IBAction func btnSend(_ sender: UIButton) {
        lblHello.text = "Hello, " + txtName.text!
    }
    
}

