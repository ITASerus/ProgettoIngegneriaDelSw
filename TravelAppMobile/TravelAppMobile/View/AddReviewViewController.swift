//
//  AddReviewViewController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 13/07/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import UIKit

class AddReviewViewController: UIViewController {

    @IBOutlet weak var titleTextField: UITextField!
    @IBOutlet weak var bodyTextView: UITextView!
    
    //Star's Outlet
    @IBOutlet weak var star1: UIButton!
    @IBOutlet weak var star2: UIButton!
    @IBOutlet weak var star3: UIButton!
    @IBOutlet weak var star4: UIButton!
    @IBOutlet weak var star5: UIButton!
    var starButtonsArray : [UIButton] = []
    var totalStars : Int = 0
    
    var structure: Structure!
    let controller = AddReviewController()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        bodyTextView.layer.borderWidth = 1
        bodyTextView.layer.borderColor =  UIColor(red: 0.80, green: 0.80, blue: 0.80, alpha: 1.00).cgColor
        bodyTextView.layer.cornerRadius = 6
        
        starButtonsArray = [star1, star2, star3, star4, star5]
    }
    
    @IBAction func starButtonPressed(_ sender: UIButton) {
        if (sender.tag == totalStars) {
            for button: UIButton in starButtonsArray {
                    button.setImage(UIImage(named: "starGrey.pdf"), for: .normal)
            }
            totalStars = 0
        } else {
            for (i, button) in starButtonsArray.enumerated() {
                if i < sender.tag {
                    button.setImage(UIImage(named: "star.pdf"), for: .normal)
                } else {
                    button.setImage(UIImage(named: "starGrey.pdf"), for: .normal)
                }
            }
            totalStars = sender.tag
        }
    }
    
    @IBAction func confirmButtonPressed(_ sender: Any) {
        controller.addReview(title: titleTextField.text!, body: bodyTextView.text, points: totalStars, userID: LoggedUserSingleton.shared.getLoggedUser()!.id, structureID: structure.id)
    }
}
