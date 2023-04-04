//
//  ViewController.swift
//  Map
//
//  Created by CSMAC12 on 2022/06/27.
//

import UIKit
import MapKit

class ViewController: UIViewController, CLLocationManagerDelegate {

    @IBOutlet var myMap: MKMapView!
    @IBOutlet var lblLocationInfo2: UILabel!
    @IBOutlet var lblLocationInfo1: UILabel!
    
    let locationManager = CLLocationManager()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        lblLocationInfo1.text = ""
        lblLocationInfo2.text = ""
        locationManager.delegate = self
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
        locationManager.requestWhenInUseAuthorization()
        locationManager.startUpdatingLocation()
        myMap.showsUserLocation = true
    }

    @IBAction func sgChangeLocation(_ sender: UISegmentedControl) {
        if sender.selectedSegmentIndex == 0 {
            
        }
        else if sender.selectedSegmentIndex == 1{
            setAnnotation(latitudeValue: 37.606816,
                          longitudeValue: 127.042383,
                          delta: 1,
                          title: "동덕여대",
                          subtitle: "월곡1동")
                          self.lblLocationInfo1.text="보고 계신 위치"
                          self.lblLocationInfo2.text="동덕여자대학교"
        }
        else if sender.selectedSegmentIndex == 2{
            setAnnotation(latitudeValue: 37.3771726,
                          longitudeValue: 126.9581737,
                          delta: 1,
                          title: "우리집",
                          subtitle: "평촌")
                          self.lblLocationInfo1.text="보고 계신 위치"
                          self.lblLocationInfo2.text="우리집"
        }
    }
    
    
    func goLocation(latitudeValue: CLLocationDegrees, longitudeValue: CLLocationDegrees, delta span: Double) -> CLLocationCoordinate2D{
        
        let pLocation = CLLocationCoordinate2DMake(latitudeValue, longitudeValue)
        let spanValue = MKCoordinateSpan(latitudeDelta: span, longitudeDelta: span)
        let pRegion = MKCoordinateRegion(center: pLocation, span: spanValue)
        myMap.setRegion(pRegion, animated: true)
        
        return pLocation
    }
    
    //viewDidLoad 에서 startUpdatingLocation해서 update되면 자동 호출
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]){
        let pLocation = locations.last
        goLocation(latitudeValue: (pLocation?.coordinate.latitude)!,
                   longitudeValue: (pLocation?.coordinate.longitude)!,
                   delta: 0.01)
        CLGeocoder().reverseGeocodeLocation(pLocation!, completionHandler:{
            (placemarks, error)->Void in
            let pm = placemarks!.first
            let country = pm!.country
            var address: String = country!
            if pm!.locality != nil {
                address += " "
                address += pm!.locality!
            }
            
            if pm!.thoroughfare != nil {
                address += " "
                address += pm!.thoroughfare!
            }
            
            self.lblLocationInfo1.text = "현재 위치"
            self.lblLocationInfo2.text = address
        })
        
        locationManager.stopUpdatingLocation()
    }
    
    // 핀 설치
    func setAnnotation(latitudeValue: CLLocationDegrees, longitudeValue: CLLocationDegrees, delta span: Double, title strTitle: String, subtitle strSubtitle: String){
        let annotation = MKPointAnnotation()
        annotation.coordinate = goLocation(latitudeValue: latitudeValue, longitudeValue: longitudeValue, delta: span)
        annotation.title = strTitle
        annotation.subtitle = strSubtitle
        myMap.addAnnotation(annotation)
    }
    
}

