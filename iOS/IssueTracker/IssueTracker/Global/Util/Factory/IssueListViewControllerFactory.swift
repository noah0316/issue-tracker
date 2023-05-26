//
//  IssueListViewControllerFactory.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/25.
//

import UIKit

struct IssueListViewControllerFactory: ViewControllerMakable {
    private static let storyBoardRepresentation = StoryBoardLiteral.issue
    
    static func make() -> UIViewController {
        let viewModel = Self.makeViewModel()
        let storyBoard = Self.makeStoryboard()
        let issueListViewController = storyBoard.instantiateViewController(
            identifier: Self.storyBoardRepresentation.storyboardId
        ) { coder in
            return IssueListViewController(coder: coder, viewModel: viewModel)
        }
        issueListViewController.title = StringLiteral.IssueListViewController.title
        issueListViewController.tabBarItem = Self.makeTabBarItem()
        
        return issueListViewController
    }
}

extension IssueListViewControllerFactory {
    private static func makeViewModel() -> IssueListViewModel {
        // UseCase 구현체로 변경할 예정입니다.
        let useCase = MockIssueListUseCase()
        let viewModel = IssueListViewModel(useCase: useCase)
        
        return viewModel
    }
    
    private static func makeTabBarItem() -> UITabBarItem {
        return UITabBarItem(
            title: StringLiteral.IssueListViewController.title,
            image: ImageMapper.exclamation.match(),
            selectedImage: ImageMapper.exclamation.match()
        )
    }
    
    private static func makeStoryboard() -> UIStoryboard {
        let storyBoard = UIStoryboard(
            name: Self.storyBoardRepresentation.storyboardName,
            bundle: Self.storyBoardRepresentation.bundle
        )
        
        return storyBoard
    }
}
