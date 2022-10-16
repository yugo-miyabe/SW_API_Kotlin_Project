# SWAPI APP

## 開発環境
[![AndroidStudio](https://img.shields.io/badge/Android%20Studio-Dolphin%20%7C%202021.3.1-blue)](https://developer.android.com/studio/)

## アプリ概要
- スターウォーズのAPIを使用したアプリ
  - [APIのドキュメント](https://swapi.dev/documentation)
- スターウォーズの、登場人物、映画、惑星を紹介する
  - 登場人物、映画、惑星の一覧を表示する
  - 登場人物、映画、惑星を検索できる
  - お気に入り機能

## 使用ライブラリ
|ライブラリ名|バージョン|用途|
|--|--|--|
| Retrofit2 | 2.9.0 | 通信ライブラリ |
| OkHttp3 | 5.0.0-alpha.2 | 通信ライブラリ |
| Timber | 5.0.1 | ログ出力 |
| Room | 2.4.3 | データベース |
| Lifecycle | 2.5.1 | LiveData|
| Navigation | 2.9.0 | 画面遷移 |
| Hilt | 2.44 | DI |



## パッケージ構成
| パッケージ名 | 説明 |
|--|--|
| activity | Activity |
| adapter | RecyclerView の Adapter |
| api | API処理の基盤 |
| base | BaseFragment、BaseViewModel |
| data | データクラス、データベースのレスポンスなど |
| di | Hilt モジュール |
| fragment | Fragment及び各FragmentのViewModel |
| repository | APIのリクエスト、データベースの操作などを行う |
| utils | Enum、WebViewのリンク、Date型の処理など |
