//
//  ViewController.swift
//  Web02
//
//  Created by CSMAC12 on 2022/06/27.
//

import UIKit
import WebKit

class ViewController: UIViewController, WKNavigationDelegate {


    @IBOutlet var txtUrl: UITextField!
    @IBOutlet var myWebView: WKWebView!
    @IBOutlet var myActivityIndicator: UIActivityIndicatorView!
    
    func loadWebPage(_ url: String){
           let myUrl = URL(string: url)
           let myRequest = URLRequest(url: myUrl!)
           myWebView.load(myRequest)
       }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        myWebView.navigationDelegate = self
        loadWebPage("http://naver.com")
    }

    @IBAction func btnGoToUrl(_ sender: UIButton) {
        let myUrl = checkUrl(txtUrl.text!)
                txtUrl.text = ""
                loadWebPage(myUrl)
    }
    
    @IBAction func btnGoSite1(_ sender: UIButton) {
        loadWebPage("http://google.com")
    }
    @IBAction func btnGoSite2(_ sender: UIButton) {
        loadWebPage("http://daum.net")
    }
    @IBAction func btnLoadHtmlString(_ sender: UIButton) {
        let htmlString = "<h1>HTML STRING</h1>"
        myWebView.loadHTMLString(htmlString, baseURL: nil)

    }
    @IBAction func btnLoadHtmlFile(_ sender: UIButton) {
        let filePath = Bundle.main.path(forResource: "htmlView", ofType: "html")
        let myUrl = URL(fileURLWithPath: filePath!)
        let myRequest = URLRequest(url: myUrl)
        myWebView.load(myRequest)
    }
    
    
  
    @IBAction func btnStop(_ sender: UIButton) {
        myWebView.stopLoading()

    }
    
    @IBAction func btnReload(_ sender: UIButton) {
        myWebView.reload()

    }
    @IBAction func btnGoBack(_ sender: UIButton) {
        myWebView.goBack()

    }
    @IBAction func btnGoForward(_ sender: UIButton) {
        myWebView.goForward()

    }
    
    //web page loading중 표시
    func webView(_ webView: WKWebView, didCommit navigation: WKNavigation){
        myActivityIndicator.startAnimating()
        myActivityIndicator.isHidden = false
    }
       
    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation){
        myActivityIndicator.stopAnimating()
        myActivityIndicator.isHidden = true
    }
       
    func webView(_webView: WKWebView, didFail navigation: WKNavigation!, withError error:Error){
        myActivityIndicator.stopAnimating()
        myActivityIndicator.isHidden = true
    }
       
       //문자열 자동 삽입 기능 구현->체크 후 없으면 붙이기
    func checkUrl(_ url: String) -> String{
        var strUrl = url
        let flag = strUrl.hasPrefix("http://")
        if !flag{
            strUrl = "http://" + strUrl
        }
        
        return strUrl
    }



}

