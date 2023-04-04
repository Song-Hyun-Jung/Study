
import UIKit

class AddViewController: UIViewController {
    @IBOutlet var dpPicker: UIDatePicker!
    @IBOutlet var tfTitle: UITextField!
    @IBOutlet var tfDetail: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        // DatePicker 에 현재 시간 설정
    
    }

    @IBAction func btnSave(_ sender: UIButton) {
        // 뷰에 입력한 값을 사용하여 DB에 추가
        
        _ = navigationController?.popViewController(animated: true)
    }
    

}

