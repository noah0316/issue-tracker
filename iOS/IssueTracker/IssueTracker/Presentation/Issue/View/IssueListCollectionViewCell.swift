//
//  IssueListCollectionViewCell.swift
//  IssueTracker
//
//  Created by ilim on 2023/05/18.
//

import UIKit

final class IssueListCollectionViewCell: UICollectionViewCell {
    @IBOutlet private weak var titleLabel: UILabel!
    @IBOutlet private weak var descriptionLabel: UILabel!
    @IBOutlet private weak var milestoneLabel: UILabel!
    @IBOutlet private weak var tagLabelStackView: UIStackView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.setupUIAppearance()
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        self.setupUIAppearance()
    }
    
    func update(with issue: Issue) {
        self.titleLabel.text = issue.title
        self.descriptionLabel.text = issue.description
        self.milestoneLabel.text = issue.milestone.title
    }
}

extension IssueListCollectionViewCell {
    private func setupUIAppearance() {
        self.setupTitleLabelUIAppearance()
        self.setupDescriptionLabelUIAppearance()
        self.setupMilestoneLabelUIAppearance()
    }
    
    private func setupTitleLabelUIAppearance() {
        self.titleLabel.font = FontStyle.title
        self.titleLabel.textColor = ColorStyle.gray900
    }
    
    private func setupDescriptionLabelUIAppearance() {
        self.descriptionLabel.font = FontStyle.body
        self.descriptionLabel.textColor = ColorStyle.gray800
    }
    
    private func setupMilestoneLabelUIAppearance() {
        self.milestoneLabel.font = FontStyle.body
        self.milestoneLabel.textColor = ColorStyle.gray800
    }
}
