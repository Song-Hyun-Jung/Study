//
//  ViewController.swift
//  DBTest
//
//  Created by CSMAC12 on 2022/07/08.
//

import UIKit
import SQLite3

class ViewController: UIViewController {

    let DB_NAME = "my_db.sqlite"
    let TABLE_NAME = "my_table"
    let COL_ID = "id"
    let COL_NAME = "name"
    
    var db: OpaquePointer? = nil //데이터베이스를 가리키는 포인터
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    @IBAction func btnOpenDatabase(_ sender: UIButton) {
        let dbFile = try! FileManager.default.url(for: .documentDirectory,
                                                  in: .userDomainMask,
                                                  appropriateFor: nil,
                                                  create: false)
            .appendingPathComponent(DB_NAME)
        
        if sqlite3_open(dbFile.path, &db) == SQLITE_OK {
            print("Successfully Opened")
            print(dbFile)
        }
        else{
            print("Unable to open DB")
        }
    }
    
    @IBAction func btnCreateTable(_ sender: UIButton) {
        let createTableString = """
            CREATE TABLE IF NOT EXISTS \(TABLE_NAME) ( \(COL_ID) INTEGER PRIMARY KEY AUTOINCREMENT, \(COL_NAME) TEXT);
            """ //띄어쓰기 주의
        var createTableStmt: OpaquePointer?
        
        print("TABLE SQL: \(createTableString)")
        
        if sqlite3_prepare_v2(db, createTableString, -1, &createTableStmt, nil) == SQLITE_OK{
            if sqlite3_step(createTableStmt) == SQLITE_DONE{
                print("Successfully created.")
            }
            sqlite3_finalize(createTableStmt)
        }
        else{
            let error = String(cString: sqlite3_errmsg(db)!)
            print("Table Error: \(error)")
        }
    }
    
    @IBAction func btnInsert(_ sender: UIButton) {
        var insertStmt: OpaquePointer?
        
        if sqlite3_prepare_v2(db, "insert into \(TABLE_NAME) values (null, ?)", -1, &insertStmt, nil) == SQLITE_OK{
            let SQLITE_TRANSIENT = unsafeBitCast(-1, to: sqlite3_destructor_type.self)
            
            if sqlite3_bind_text(insertStmt, 1, "test1", -1, SQLITE_TRANSIENT) != SQLITE_OK{
                let errmsg = String(cString: sqlite3_errmsg(db)!)
                print("Text Binding Failure: \(errmsg)")
                return
            }
            
            if sqlite3_step(insertStmt) == SQLITE_DONE{
                print("Successfully inserted.")
            }
            else{
                print("insert error.")
            }
            
            sqlite3_finalize(insertStmt)
        }
        else{
            print("Insert statement is not prepared.")
        }
    }
    
    @IBAction func btnSelectAll(_ sender: UIButton) {
        var findName = "test1"
        let sql = "select * from \(TABLE_NAME)" //where \(COL_NAME) = '\(findName)'"
        
        var queryStmt: OpaquePointer?
        
        if sqlite3_prepare(db, sql, -1, &queryStmt, nil) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("Reading Error: \(errmsg)")
            return
        }
        
        while(sqlite3_step(queryStmt) == SQLITE_ROW){
            let id = sqlite3_column_int(queryStmt, 0)
            let name = String(cString: sqlite3_column_text(queryStmt, 1))
            print("id: \(id) name: \(name)")
        }
        
        sqlite3_finalize(queryStmt)
    }
    
    @IBAction func btnUpdate(_ sender: UIButton) {
        /*
        let query = "update \(TABLE_NAME) set \(COL_NAME) = ? where \(COL_ID) = ?"
        
        var updateStmt: OpaquePointer?
        
        if sqlite3_prepare(db, query, -1, &updateStmt, nil) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error preparing update: \(errmsg)")
            return
        }
        
        let SQLITE_TRANSIENT = unsafeBitCast(-1, to: sqlite3_destructor_type.self)
        
        //prepared statement 매개변수 연결(binding)
        if sqlite3_bind_text(updateStmt, 1, "my_id", -1, SQLITE_TRANSIENT) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("Text Binding Failure: \(errmsg)")
            return
        }
        
        if sqlite3_bind_int(updateStmt, 2, 1) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("Integer Binding Failure: \(errmsg)")
            return
        }
        
        if sqlite3_step(updateStmt) != SQLITE_DONE{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("Update Failure: \(errmsg)")
            return
        }
        
        sqlite3_finalize(updateStmt)
         */
        doUpdateDB(1, "my_id")
    }
    
    func doUpdateDB(_ targetId: Int, _ changeName: String){
        let query = "update \(TABLE_NAME) set \(COL_NAME) = ? where \(COL_ID) = ?"
        
        var updateStmt: OpaquePointer?
        
        if sqlite3_prepare(db, query, -1, &updateStmt, nil) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error preparing update: \(errmsg)")
            return
        }
        
        let SQLITE_TRANSIENT = unsafeBitCast(-1, to: sqlite3_destructor_type.self)
        
        //prepared statement 매개변수 연결(binding)
        if sqlite3_bind_text(updateStmt, 1, changeName, -1, SQLITE_TRANSIENT) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("Text Binding Failure: \(errmsg)")
            return
        }
        
        if sqlite3_bind_int(updateStmt, 2, Int32(targetId)) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("Integer Binding Failure: \(errmsg)")
            return
        }
        
        if sqlite3_step(updateStmt) != SQLITE_DONE{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("Update Failure: \(errmsg)")
            return
        }
        
        sqlite3_finalize(updateStmt)
    }
    
    @IBAction func btnDelete(_ sender: UIButton) {
        let query = "delete from \(TABLE_NAME) where \(COL_ID) = ?"
        var deleteStmt: OpaquePointer?
        
        if sqlite3_prepare(db, query, -1, &deleteStmt, nil) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error preparing stmt: \(errmsg)")
            return
        }
        
        var id = 1
        bindIntParams(deleteStmt!, no: 1, param: id)
        
        if sqlite3_step(deleteStmt) != SQLITE_DONE{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("Delete Failure: \(errmsg)")
            return
        }
        sqlite3_finalize(deleteStmt)
    }
    
    func bindIntParams(_ stmt: OpaquePointer, no: Int, param: Int){
        if sqlite3_bind_int(stmt, Int32(no), Int32(param)) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("Text Binding Failure: \(errmsg)")
            return
        }
    }
    @IBAction func btnDropTable(_ sender: UIButton) {
        if sqlite3_exec(db, "drop table if exists \(TABLE_NAME)", nil, nil, nil) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("Drop Error: \(errmsg)")
            return
        }
    }
    
    @IBAction func btnCloseDatabase(_ sender: UIButton) {
        if sqlite3_close(db) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("Database Close Error: \(errmsg)")
            return
        }
    }
    
}

