//
//  ViewController.swift
//  PageControlMission
//
//  Created by CSMAC12 on 2022/06/28.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet var pageNo: UILabel!
    @IBOutlet var pageControl: UIPageControl!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        pageControl.pageIndicatorTintColor = UIColor.blue
        pageControl.currentPageIndicatorTintColor = UIColor.red
        
        pageControl.numberOfPages = 10
        pageControl.currentPage = 1
        
        pageNo.text = String(1)
    }

    @IBAction func pageChange(_ sender: UIPageControl) {
        pageNo.text = String(pageControl.currentPage + 1)
    }
    
}

