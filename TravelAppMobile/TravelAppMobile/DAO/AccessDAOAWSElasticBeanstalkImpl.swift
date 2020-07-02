//
//  AccessDAOAWSElasticBeanstalkImpl.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 30/06/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

public class AccessDAOAWSElasticbeanstalkImpl: AccessDAOProtocol {
    
    private let LOG_IN = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/api/auth/signin"
    
    //Output con closure come dizionario per gestire sia errori che accesso effettuato correttamente
    func logIn(usernameOrEmail: String, password: String, completion: @escaping ([String: Any]) -> Void) {
        let body: [String: Any] = ["usernameOrEmail": usernameOrEmail, "password": password]
        
        let jsonData = try? JSONSerialization.data(withJSONObject: body)

        // create post request
        var request = URLRequest(url: URL.init(string: LOG_IN)!)
        request.httpMethod = "POST"
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.setValue("\(jsonData!.count)", forHTTPHeaderField: "Content-Length")

        // insert json data to the request
        request.httpBody = jsonData

        let task = URLSession.shared.dataTask(with: request) { data, response, error in
            guard let data = data, error == nil else {
                print(error?.localizedDescription ?? "No data")
                return
            }
            
            let responseJSON = try? JSONSerialization.jsonObject(with: data, options: [])
            completion(responseJSON as! [String : Any])
        }

        task.resume()
    }
}
