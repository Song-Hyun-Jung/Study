//
//  ViewController.swift
//  ImageMission
//
//  Created by CSMAC12 on 2022/06/23.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet var imgView: UIImageView!
    var image1: UIImage?
    var image2: UIImage?
    var image3: UIImage?
    var image4: UIImage?
    var image5: UIImage?
    var image6: UIImage?
    var index = 1
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        image1 = UIImage(named: "1.png")
        image2 = UIImage(named: "2.png")
        image3 = UIImage(named: "3.png")
        image4 = UIImage(named: "4.png")
        image5 = UIImage(named: "5.png")
        image6 = UIImage(named: "6.png")
        
        imgView.image = image1
    }


    @IBAction func btnBack(_ sender: UIButton) {
        
        if(index >= 1){
            index -= 1
            imgView.image = UIImage(named: String(index) + ".png")
        }
    }
    @IBAction func btnNext(_ sender: UIButton) {
        
        if(index <= 6){
            index += 1
            imgView.image = UIImage(named: String(index) + ".png")
        }
    }
}

