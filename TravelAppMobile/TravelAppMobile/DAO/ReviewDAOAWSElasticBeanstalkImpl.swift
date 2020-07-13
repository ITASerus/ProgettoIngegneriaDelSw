//
//  ReviewDAOAWSElasticBeanstalkImpl.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 25/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

public class ReviewDAOAWSElasticbeanstalkImpl: ReviewDAOProtocol {
    private let GET_USER_BY_REVIEW_ID = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/reviews/id="
    private let ADD_REVIEW = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/reviews/create"
    
    func getUserByReviewID(id: Int) -> User? {
        let FINAL_URL = GET_USER_BY_REVIEW_ID + id.description + "/getUser"
        
        let dataStructures = try! Data.init(contentsOf: URL.init(string: FINAL_URL)!)
        
        do {
            let decoder: JSONDecoder = JSONDecoder.init()
            let user: User = try decoder.decode(User.self, from: dataStructures)
                        
            return user
        } catch let e {
            print(e)
        }

        return nil
    }
    
    func addReview(title: String, body: String, points: Int, userID: Int, structureID: Int, completion: @escaping ([String: Any]) -> Void) {
        let body: [String: Any] = [
            "title": title,
            "description": body,
            "points": points,
            "structureID": structureID,
            "userID": userID
        ]
        let token = LoggedUserSingleton.shared.getLoggedUser()!.token!
        
        print(body)
        
        let jsonData = try? JSONSerialization.data(withJSONObject: body)

        // create post request
        var request = URLRequest(url: URL.init(string: ADD_REVIEW)!)
        request.httpMethod = "POST"
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.addValue("Bearer " + token, forHTTPHeaderField: "Authorization")
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


       
       
