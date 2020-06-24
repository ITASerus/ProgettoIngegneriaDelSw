//
//  ReviewDetailViewController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 20/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import UIKit

class ReviewDetailViewController: UIViewController {

    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var bodyTextView: UITextView!
    @IBOutlet weak var authorLabel: UILabel!
    
    var review: Review!
    var user: User!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        titleLabel.text = review.title
        bodyTextView.text = review.description
        //authorLabel.text = review.author
    }
}
