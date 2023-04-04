//
//  TableTableViewController.swift
//  Table
//
//  Created by CSMAC12 on 2022/06/30.
//

import UIKit

var items = ["책 구매", "철수와 약속", "스터디 준비하기"]
var itemsImageFile = ["cart.png", "clock.png", "pencil.png"]

class TableViewController: UITableViewController {

    @IBOutlet var tvListView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        self.navigationItem.leftBarButtonItem = self.editButtonItem
    }
    
    //항목 갱신 -> pop 하면 원래 있던 기존화면이 스택에서 나오는거라 추가한 데이터가 반영이 안되기 때문에 갱신 필요
    override func viewWillAppear(_ animated: Bool){
        tvListView.reloadData()
    }

    // MARK: - Table view data source

    //항목 칸 수
    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    //항목의 총 개수
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return items.count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "myCell", for: indexPath)

        // Configure the cell...
        //몇번째 항목 값을 꺼내라
        cell.textLabel?.text = items[(indexPath as NSIndexPath).row] //0 1 2...
        cell.imageView?.image = UIImage(named: itemsImageFile[(indexPath as NSIndexPath).row])

        return cell
    }
    

    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete { //항목 삭제
            // Delete the row from the data source
            //원본 삭제
            items.remove(at: (indexPath as NSIndexPath).row)
            itemsImageFile.remove(at: (indexPath as NSIndexPath).row)
            
            tableView.deleteRows(at: [indexPath], with: .fade) //화면 요소를 지워주는 작업. 미리 되어있음
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    
    //delete->삭제로 문자열 변경
    override func tableView(_ tableView: UITableView, titleForDeleteConfirmationButtonForRowAt indexPath: IndexPath) -> String? {
        return "삭제"
    }
    

    //목록 순서 바꾸기
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {
        let itemToMove = items[(fromIndexPath as NSIndexPath).row]
        let itemImageToMove = itemsImageFile[(fromIndexPath as NSIndexPath).row]
        
        items.remove(at: (fromIndexPath as NSIndexPath).row)
        itemsImageFile.remove(at: (fromIndexPath as NSIndexPath).row)
        
        items.insert(itemToMove, at: (to as NSIndexPath).row)
        itemsImageFile.insert(itemImageToMove, at: (to as NSIndexPath).row)
        

    }
    

    
    //값 전달
    override func prepare(for segue: UIStoryboardSegue, sender: Any?){
        //Get the new view controller using segue.destinationViewController
        //Pass the selected object to the new view controller.
        
        if segue.identifier == "sgDetail"{
            let cell = sender as! UITableViewCell
            let indexPath = self.tvListView.indexPath(for: cell)
            let detailView = segue.destination as! DetailViewController
            detailView.receiveItem(items[((indexPath! as NSIndexPath).row)])
            print((indexPath! as NSIndexPath).row)
            detailView.receiveRow((indexPath! as NSIndexPath).row)
            
        }
    }
    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
