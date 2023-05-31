//
//  IssueListView.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/19.
//

import UIKit

final class IssueListView: UICollectionView {
    private lazy var issueListViewDataSource: IssueListViewDataSource = self.makeIssueListViewDataSource()
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.setupUIAppearance()
    }
    
    func update(with issues: IssueCollection) {
        var snapshot = IssueListViewSnapshot()
        snapshot.appendSections([.main])
        snapshot.appendItems(issues.allIssues())
        self.issueListViewDataSource.apply(snapshot)
    }
}

extension IssueListView {
    private func setupUIAppearance() {
        self.setCollectionViewLayout(self.makeIssueListViewLayout(), animated: true)
        self.register(
            NibFactory.makeNib(for: .issueListCollectionViewCell),
            forCellWithReuseIdentifier: IssueListCollectionViewCell.identifier
        )
    }
    
    private func makeIssueListViewDataSource() -> IssueListViewDataSource {
        // swiftlint:disable:next line_length
        return IssueListViewDataSource(collectionView: self) { issueListView, indexPath, issue -> IssueListCollectionViewCell? in
            let issueListCollectionViewCell = issueListView.dequeueReusableCell(
                withReuseIdentifier: IssueListCollectionViewCell.identifier,
                for: indexPath
            ) as? IssueListCollectionViewCell
            issueListCollectionViewCell?.update(with: issue)
            issueListCollectionViewCell?.sizeToFit()
            
            return issueListCollectionViewCell
        }
    }
    
    private func makeCellLayoutSize() -> NSCollectionLayoutSize {
        let fullWidthRatio: CGFloat = 1
        let estimatedCellHeight: CGFloat = 148
        
        return NSCollectionLayoutSize(
            widthDimension: .fractionalWidth(fullWidthRatio),
            heightDimension: .estimated(estimatedCellHeight)
        )
    }
    
    private func makeIssueListViewLayout() -> UICollectionViewCompositionalLayout {
        let itemSize = self.makeCellLayoutSize()
        let item = NSCollectionLayoutItem(layoutSize: itemSize)
        let groupSize = self.makeCellLayoutSize()
        let group = NSCollectionLayoutGroup.vertical(layoutSize: groupSize, subitems: [item])
        let section = NSCollectionLayoutSection(group: group)
        let layout = UICollectionViewCompositionalLayout(section: section)
        
        return layout
    }
}

extension IssueListView {
    private enum Section {
        case main
    }
    
    private typealias IssueListViewDataSource = UICollectionViewDiffableDataSource<Section, Issue>
    private typealias IssueListViewSnapshot = NSDiffableDataSourceSnapshot<Section, Issue>
}
