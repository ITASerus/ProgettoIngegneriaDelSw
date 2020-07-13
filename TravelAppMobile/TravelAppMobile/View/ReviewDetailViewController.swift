//
//  ReviewDetailViewController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 20/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import UIKit

class ReviewDetailViewController: UIViewController {

    @IBOutlet weak var viewBackground: UIView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var bodyTextView: UITextView!
    @IBOutlet weak var authorLabel: UILabel!
    @IBOutlet weak var authorImageView: UIImageView!
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var pointsImageView: UIImageView!
    
    var review: Review!
    var user: User!
    var backgroundColor: UIColor!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        viewBackground.layer.cornerRadius = 20
        viewBackground.layer.shadowRadius = 5
        viewBackground.layer.shadowOpacity = 0.5
        viewBackground.layer.shadowOffset = CGSize(width: 0, height: 3)
        viewBackground.backgroundColor = backgroundColor
        bodyTextView.backgroundColor = backgroundColor
        
        titleLabel.text = review.title
        bodyTextView.text = review.description
        authorLabel.text = review.firstName
        authorImageView.image = review.imageDownloaded?.getImage()
        
        let points = review.points ?? 0.0
        pointsImageView.image = UIImage (imageLiteralResourceName: GeneralReusables.starsImageAssetName(avgPoints: points))
        
        // Date Management
        var jsonDate = review.date
        let dateFormatterGet = DateFormatter()
        dateFormatterGet.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS+SSSS"
        let dateFormatterPrint = DateFormatter()
        dateFormatterPrint.dateFormat = "dd/MM/yyyy"
        let datee = dateFormatterGet.date(from: jsonDate)
        jsonDate =  dateFormatterPrint.string(from: datee!)
               
        dateLabel.text = jsonDate
    }
}
