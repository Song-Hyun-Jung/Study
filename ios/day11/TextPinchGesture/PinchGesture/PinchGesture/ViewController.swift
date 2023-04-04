//
//  ViewController.swift
//  PinchGesture
//
//  Created by CSMAC12 on 2022/07/07.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet var txtPinch: UILabel!
    
    var initialFontSize: CGFloat! //기본 폰트 사이즈 기억하도록
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        let pinch = UIPinchGestureRecognizer(target: self, action:
                                                #selector(ViewController.doPinch(_:)))
        self.view.addGestureRecognizer(pinch)
    }

    @objc func doPinch(_ pinch: UIPinchGestureRecognizer){
        if(pinch.state == UIGestureRecognizer.State.began){
            initialFontSize = txtPinch.font.pointSize
        }
        else{
            txtPinch.font = txtPinch.font.withSize(initialFontSize * pinch.scale)
        }
    }
}

