//
//  UserDAOAWSElasticBeanstalkImpl.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 25/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation
import UIKit

public class UserDAOAWSElasticbeanstalkImpl: UserDAOProtocol {
    private let GET_BY_USERNAME_OR_EMAIL = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/users/usernameOrEmail/"
    
    func getByUsernameOrEmail(usernameOrEmail: String, completion: @escaping (User?) -> Void) {
        let FINAL_URL = GET_BY_USERNAME_OR_EMAIL + "username="+usernameOrEmail+"&email="+usernameOrEmail
        
        let dataStructures = try! Data.init(contentsOf: URL.init(string: FINAL_URL)!)
        
        do {
            let decoder: JSONDecoder = JSONDecoder.init()
            var user: User = try decoder.decode(User.self, from: dataStructures)
            
            // Manage image
            /*if (user.image != nil) {
                if(user.imageDownloaded == nil) {
                    let imageURL = URL(string: (user.image!))

                    // just not to cause a deadlock in UI!
                    DispatchQueue.global().async {
                        let imageData = try? Data(contentsOf: imageURL!)

                        let image = UIImage(data: imageData!)
                        DispatchQueue.main.async {
                            user.imageDownloaded = UIImageCodable.init(withImage: image!)
                        }
                    }
                }
            } else {
                let image = UIImage.init(named: "DefaultImageWBlackShade.pdf")
                user.imageDownloaded = UIImageCodable.init(withImage: image!)
            }*/
            
            completion(user)
            
        } catch let e {
            print(e)
        }
    }
   
}
