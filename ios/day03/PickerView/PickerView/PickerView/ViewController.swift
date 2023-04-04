//
//  ViewController.swift
//  PickerView
//
//  Created by CSMAC12 on 2022/06/24.
//

import UIKit

class ViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    let MAX_ARRAY_NUM = 10
    let PICKER_VIEW_COLUMN = 2
    var imageFileName = ["1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg",
                         "6.jpg", "7.jpg", "8.jpg", "9.jpg", "10.jpg"]
    let PICKER_VIEW_HEIGHT:CGFloat = 80 //스크롤 한행 높이 키우기
    
    //이미지 객체 읽어와서 액자에 넣기 위해 배열 만듬
    var imageArray = [UIImage?]()

    @IBOutlet var pickerImage: UIPickerView!
    @IBOutlet var lblImageFileName: UILabel!
    @IBOutlet var imageView: UIImageView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        //이미지들 불러오기
        for i in 0 ..< MAX_ARRAY_NUM {
            let image = UIImage(named: imageFileName[i])
            imageArray.append(image)
        }
        
        lblImageFileName.text = imageFileName[0]
        imageView.image = imageArray[0]
    }

    //returns the number of columns to display
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return PICKER_VIEW_COLUMN
    }
    
    //returns the # of rows in each component
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return imageFileName.count
    }

    /*
    //스크롤 내용에 글씨가 나오도록
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return imageFileName[row]
    }
    */
    
    //스크롤 내용에 이미지가 나오도록
    func pickerView(_ pickerView: UIPickerView, viewForRow row: Int, forComponent component: Int, reusing view: UIView?) -> UIView {
        let imageView = UIImageView(image:imageArray[row])
        imageView.frame = CGRect(x: 0, y: 0, width: 100, height: 150)
        
        return imageView
    }
    //룰렛 높이 변경
    func pickerView(_ pickerView: UIPickerView, rowHeightForComponent component: Int) -> CGFloat {
        return PICKER_VIEW_HEIGHT
    }
    
    //선택된 항목 해당 내용 뷰에 출력하기
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int){
        if(component == 0){
            lblImageFileName.text = imageFileName[row]
        }
        else{
            imageView.image = imageArray[row]
        }
    }
}

