
import UIKit

var itemsImageFile = ["cart.png", "clock.png", "pencil.png"]

class AddViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource
{
    
    let manager = DBManager()
    
    @IBOutlet var dpPicker: UIDatePicker!
    @IBOutlet var tfTitle: UITextField!
    @IBOutlet var tfDetail: UITextField!
    @IBOutlet var pickerImage: UIPickerView!
    @IBOutlet var imgView: UIImageView!
    
    
    var imageArray = [UIImage?]()
    
    let PICKER_VIEW_HEIGHT:CGFloat = 100 //스크롤 한행 높이 키우기
    var imgName: String = itemsImageFile[0]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
       
        // DatePicker 에 현재 시간 설정
        
        //pickerView
        for i in 0 ..< itemsImageFile.count{
            let image = UIImage(named: itemsImageFile[i])
            imageArray.append(image)
        }
        
        imgView.image = imageArray[0]
    
    }

    @IBAction func btnSave(_ sender: UIButton) {
        // 뷰에 입력한 값을 사용하여 DB에 추가
        let insertDate = Int32(dpPicker.date.timeIntervalSince1970)
        let getTitle = tfTitle.text!
        let getDetail = tfDetail.text!
        print("insertDate: \(insertDate)" )
        
        manager.doInsert(TaskDto(id: -1, title: getTitle, date: insertDate, detail: getDetail, icon: imgName))
        
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
    
    
}

