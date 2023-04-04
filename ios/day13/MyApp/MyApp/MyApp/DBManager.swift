import Foundation

class DBManager {
    
    var db : OpaquePointer?
    
//    앱을 실행할 때 수행
    func initDatabase() {
        openDatabase()
        createTable()
        closeDatabase()
    }
    
//    DB 사용 전에 호출
    private func openDatabase() {
        
    }
    
//    테이블 생성
    private func createTable() {
        
    }
    
//    DB 완료 후 호출 
    private func closeDatabase() {
        
    }
    
//    items 배열에 DB의 내용 전체를 추가
    func readAllData() {
//        샘플이므로 DB 구현 시 주석 처리
        items.append(TaskDto(id: 1, title: "hello", date: 1625728889, detail: "hi", icon: "clock.png"))
        items.append(TaskDto(id: 2, title: "안녕하세요", date: 1625728889, detail: "안녕", icon: "cart.png"))
    }

//    항목 추가

    
//    항목 수정

    
//    항목 삭제
}
