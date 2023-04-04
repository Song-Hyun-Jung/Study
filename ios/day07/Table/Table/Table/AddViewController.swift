//
//  AddViewController.swift
//  Table
//
//  Created by CSMAC12 on 2022/06/30.
//

import UIKit

class AddViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource{

    @IBOutlet var tfAddItem: UITextField!
    @IBOutlet var imgView: UIImageView!
    @IBOutlet var pickerImage: UIPickerView!
    
    var imageArray = [UIImage?]()
    
    let PICKER_VIEW_HEIGHT:CGFloat = 150 //스크롤 한행 높이 키우기
    var imgName: String = itemsImageFile[0]

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
        for i in 0 ..< itemsImageFile.count{
            let image = UIImage(named: itemsImageFile[i])
            imageArray.append(image)
        }
        
        imgView.image = imageArray[0]
    }
    

    @IBAction func btnAddItem(_ sender: UIButton) {
        items.append(tfAddItem.text!)
        //itemsImageFile.append("clock.png")
        itemsImageFile.append(imgName)
        tfAddItem.text = ""
        _ = navigationController?.popViewController(animated: true)
    }
    
    
    
    //returns the number of columns to display
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    //returns the # of rows in each component
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return itemsImageFile.count
    }
    /*
    //스크롤 내용에 글씨가 나오도록
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return itemsImageFile[row]
    }
    */
    
    //스크롤 내용에 이미지가 나오도록
    func pickerView(_ pickerView: UIPickerView, viewForRow row: Int, forComponent component: Int, reusing view: UIView?) -> UIView {
        let imageView = UIImageView(image: imageArray[row])
        imageView.frame = CGRect(x: 0, y: 0, width: 100, height: 150)
        
        return imageView
    }
    
    //룰렛 높이 변경
    func pickerView(_ pickerView: UIPickerView, rowHeightForComponent component: Int) -> CGFloat {
        return PICKER_VIEW_HEIGHT
    }
    
    //선택된 항목 해당 내용 뷰에 출력하기
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int){
            imgView.image = imageArray[row]
            imgName = itemsImageFile[row]
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
