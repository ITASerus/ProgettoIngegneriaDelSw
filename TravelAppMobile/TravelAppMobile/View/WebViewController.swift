//
//  WebViewController.swift
//  DrenTravelApp
//
//  Created by Piero Junior Gaetani on 18/05/2020.
//  Copyright © 2020 Piero Junior Gaetani. All rights reserved.
//

import UIKit
import WebKit

class WebViewController:  UIViewController, WKNavigationDelegate {
       

    var webView: WKWebView!

          override func loadView() {
              webView = WKWebView()
              webView.navigationDelegate = self
              view = webView
          }
          
          override func viewDidLoad() {
              super.viewDidLoad()
              // Do any additional setup after loading the view.
           
              
            let url = URL(string: singleton.shared.endpointGetToSearch)!
              webView.load(URLRequest(url: url))
              webView.allowsBackForwardNavigationGestures = false
              
              //MARK: StatusBar
              if #available(iOS 13.0, *) {
                  let app = UIApplication.shared
                  let statusBarHeight: CGFloat = app.statusBarFrame.size.height
                  
                  let statusbarView = UIView()
                  statusbarView.backgroundColor = UIColor(red:0.00, green:0.60, blue:0.65, alpha:1.0)
                  view.addSubview(statusbarView)
                      
                  statusbarView.translatesAutoresizingMaskIntoConstraints = false
                  statusbarView.heightAnchor.constraint(equalToConstant: statusBarHeight).isActive = true
                  statusbarView.widthAnchor.constraint(equalTo: view.widthAnchor, multiplier: 1.0).isActive = true
                  statusbarView.topAnchor.constraint(equalTo: view.topAnchor).isActive = true
                  statusbarView.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
              } else {
                  let statusBar = UIApplication.shared.value(forKeyPath: "statusBarWindow.statusBar") as? UIView
                  statusBar?.backgroundColor = UIColor(red:0.00, green:0.60, blue:0.65, alpha:1.0)
              }
          }
          
          override var preferredStatusBarStyle: UIStatusBarStyle {
              return .lightContent
          }
      }

        
      
