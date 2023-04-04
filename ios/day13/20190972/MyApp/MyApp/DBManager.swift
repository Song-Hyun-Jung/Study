import Foundation
import SQLite3

class DBManager {
    
    let DB_NAME = "my_db.sqlite"
    let TABLE_NAME = "my_table"
    let COL_ID = "id"
    let COL_TITLE = "title"
    let COL_DATE = "col_date"
    let COL_DETAIL = "detail"
    let COL_ICON = "icon"
    
    var db : OpaquePointer?
    
//    앱을 실행할 때 수행
    func initDatabase() {
        openDatabase()
        createTable()
        closeDatabase()
    }
    
//    DB 사용 전에 호출
    private func openDatabase() {
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
    
//    테이블 생성
    private func createTable() {
        let createTableString = """
            CREATE TABLE IF NOT EXISTS \(TABLE_NAME) ( \(COL_ID) INTEGER PRIMARY KEY AUTOINCREMENT, \(COL_TITLE) TEXT, \(COL_DATE) INTEGER, \(COL_DETAIL) TEXT,
                        \(COL_ICON) TEXT);
            """
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
    
//    DB 완료 후 호출 
    private func closeDatabase() {
        if sqlite3_close(db) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("Database Close Error: \(errmsg)")
            return
        }
    }
    
//    items 배열에 DB의 내용 전체를 추가
    func readAllData() {
//        샘플이므로 DB 구현 시 주석 처리
       // items.append(TaskDto(id: 1, title: "hello", date: 1625728889, detail: "hi", icon: "clock.png"))
      // items.append(TaskDto(id: 2, title: "안녕하세요", date: 1625728889, detail: "안녕", icon: "cart.png"))
        openDatabase()
        let sql = "select * from \(TABLE_NAME) "
        
        var queryStmt: OpaquePointer?
        
        if sqlite3_prepare(db, sql, -1, &queryStmt, nil) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("Reading Error: \(errmsg)")
            return
        }
        
        while(sqlite3_step(queryStmt) == SQLITE_ROW){
            let id = sqlite3_column_int(queryStmt, 0)
            let title = String(cString: sqlite3_column_text(queryStmt, 1))
            let date = sqlite3_column_int(queryStmt, 2)
            let detail = String(cString: sqlite3_column_text(queryStmt, 3))
            let icon = String(cString: sqlite3_column_text(queryStmt, 4))
            print("id: \(id) title: \(title) date: \(date) detail: \(detail) icon: \(icon)")
            
            items.append(TaskDto(id: Int(id), title: title, date: date, detail: detail, icon: icon))
        }
        
        sqlite3_finalize(queryStmt)
        closeDatabase()
    }

//    항목 추가
    func doInsert(_ insertValue: TaskDto){
        openDatabase()
        var insertStmt: OpaquePointer?
        
        if sqlite3_prepare_v2(db, "insert into \(TABLE_NAME) values (null, ?, ?, ?, ?)", -1, &insertStmt, nil) == SQLITE_OK{
            let SQLITE_TRANSIENT = unsafeBitCast(-1, to: sqlite3_destructor_type.self)
            
            if sqlite3_bind_text(insertStmt, 1, insertValue.title, -1, SQLITE_TRANSIENT) != SQLITE_OK{
                let errmsg = String(cString: sqlite3_errmsg(db)!)
                print("Text Binding Failure: \(errmsg)")
                return
            }
            if sqlite3_bind_int(insertStmt, 2, insertValue.date) != SQLITE_OK{
                let errmsg = String(cString: sqlite3_errmsg(db)!)
                print("Text Binding Failure: \(errmsg)")
                return
            }
            if sqlite3_bind_text(insertStmt, 3, insertValue.detail, -1, SQLITE_TRANSIENT) != SQLITE_OK{
                let errmsg = String(cString: sqlite3_errmsg(db)!)
                print("Text Binding Failure: \(errmsg)")
                return
            }
            if sqlite3_bind_text(insertStmt, 4, insertValue.icon, -1, SQLITE_TRANSIENT) != SQLITE_OK{
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
            testRead()
            closeDatabase()
            
        }
        else{
            print("Insert statement is not prepared.")
        }
    }

    
//    항목 수정
    func doUpdateDB(_ targetId: Int, _ changeTitle: String, _ changeDetail: String){
        openDatabase()
        let query = "update \(TABLE_NAME) set \(COL_TITLE) = ?, \(COL_DETAIL) = ? where \(COL_ID) = ?"
        
        var updateStmt: OpaquePointer?
        
        if sqlite3_prepare(db, query, -1, &updateStmt, nil) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error preparing update: \(errmsg)")
            return
        }
        
        let SQLITE_TRANSIENT = unsafeBitCast(-1, to: sqlite3_destructor_type.self)
        
        //prepared statement 매개변수 연결(binding)
        if sqlite3_bind_text(updateStmt, 1, changeTitle, -1, SQLITE_TRANSIENT) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("Text Binding Failure: \(errmsg)")
            return
        }
        if sqlite3_bind_text(updateStmt, 2, changeDetail, -1, SQLITE_TRANSIENT) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("Text Binding Failure: \(errmsg)")
            return
        }
        
        if sqlite3_bind_int(updateStmt, 3, Int32(targetId)) != SQLITE_OK{
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
        closeDatabase()
    }

    
//    항목 삭제
    func doDelete(_ targetId: Int){
        print("삭제하러 들어옴 \(targetId)")
        openDatabase()
        let query = "delete from \(TABLE_NAME) where \(COL_ID) = ?"
        var deleteStmt: OpaquePointer?
        
        if sqlite3_prepare(db, query, -1, &deleteStmt, nil) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("error preparing stmt: \(errmsg)")
            return
        }
        
        bindIntParams(deleteStmt!, no: 1, param: targetId)
        
        if sqlite3_step(deleteStmt) != SQLITE_DONE{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("Delete Failure: \(errmsg)")
            return
        }
        sqlite3_finalize(deleteStmt)
        print("Ddd")
        testRead()
        closeDatabase()
    }
    func bindIntParams(_ stmt: OpaquePointer, no: Int, param: Int){ //삭제 바인딩 함수화
        if sqlite3_bind_int(stmt, Int32(no), Int32(param)) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("Text Binding Failure: \(errmsg)")
            return
        }
    }
    
    //데이터베이스 항목 콘솔에 찍어보기
    func testRead() {
        openDatabase()
        let sql = "select * from \(TABLE_NAME) "
        
        var queryStmt: OpaquePointer?
        
        if sqlite3_prepare(db, sql, -1, &queryStmt, nil) != SQLITE_OK{
            let errmsg = String(cString: sqlite3_errmsg(db)!)
            print("Reading Error: \(errmsg)")
            return
        }
        
        while(sqlite3_step(queryStmt) == SQLITE_ROW){
            let id = sqlite3_column_int(queryStmt, 0)
            let title = String(cString: sqlite3_column_text(queryStmt, 1))
            let date = sqlite3_column_int(queryStmt, 2)
            let detail = String(cString: sqlite3_column_text(queryStmt, 3))
            let icon = String(cString: sqlite3_column_text(queryStmt, 4))
            print("id: \(id) title: \(title) date: \(date) detail: \(detail) icon: \(icon)")

        }
        
        sqlite3_finalize(queryStmt)
        closeDatabase()
    }

}
