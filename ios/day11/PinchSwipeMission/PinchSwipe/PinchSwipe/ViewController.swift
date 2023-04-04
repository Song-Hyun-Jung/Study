//
//  ViewController.swift
//  PinchSwipe
//
//  Created by CSMAC12 on 2022/07/07.
//

import UIKit

var images = ["cat1.jpg", "cat2.jpg", "cat3.jpg", "01.png", "02.png", "03.png", "04.png", "05.png", "06.png"]

class ViewController: UIViewController {

    @IBOutlet var imgView: UIImageView!
    @IBOutlet var pageControl: UIPageControl!
    
   // var currPage = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        pageControl.numberOfPages = images.count
        pageControl.currentPage = 0
        
        pageControl.pageIndicatorTintColor = UIColor.gray
        pageControl.currentPageIndicatorTintColor = UIColor.red
        
        imgView.image = UIImage(named: images[0])
        
        let swipeRight = UISwipeGestureRecognizer(target: self, action: #selector(ViewController.respondToSwipeGesture(_:)))
        swipeRight.direction = UISwipeGestureRecognizer.Direction.right
        self.view.addGestureRecognizer(swipeRight)
        
        let swipeLeft = UISwipeGestureRecognizer(target: self, action: #selector(ViewController.respondToSwipeGesture(_:)))
        swipeLeft.direction = UISwipeGestureRecognizer.Direction.left
        self.view.addGestureRecognizer(swipeLeft)
        
        
        let pinch = UIPinchGestureRecognizer(target:self, action:
                                                #selector(ViewController.doPinch(_:)))
        self.view.addGestureRecognizer(pinch)
    }

    @IBAction func pageChange(_ sender: UIPageControl) {
        imgView.image = UIImage(named: images[pageControl.currentPage])
    }
    
    @objc func respondToSwipeGesture(_ gesture: UIGestureRecognizer){
        if let swipeGesture = gesture as? UISwipeGestureRecognizer {
            imgView.image = UIImage(named: images[pageControl.currentPage])
            switch swipeGesture.direction{
                case UISwipeGestureRecognizer.Direction.right:
                if(pageControl.currentPage + 1 < images.count){
                    pageControl.currentPage = pageControl.currentPage + 1
                    imgView.image = UIImage(named: images[pageControl.currentPage])
                    print(pageControl.currentPage)
                }
                case UISwipeGestureRecognizer.Direction.left:
                if(pageControl.currentPage - 1 >= 0){
                    pageControl.currentPage = pageControl.currentPage - 1
                    imgView.image = UIImage(named: images[pageControl.currentPage])
                    print(pageControl.currentPage)
                }
                default:
                    break
            }
        }
    }
    
    @objc func doPinch(_ pinch: UIPinchGestureRecognizer){
        imgView.transform = imgView.transform.scaledBy(x: pinch.scale, y: pinch.scale)
        pinch.scale = 1
    }
    
}

