# SWAPI APP

## 開発環境
[![AndroidStudio](https://img.shields.io/badge/Android%20Studio-Dolphin%20%7C%202021.3.1-blue)](https://developer.android.com/studio/)

## アプリ概要
スターウォーズのAPIを使用したアプリ

[ドキュメント](https://swapi.dev/documentation)

API:https://swapi.dev/

スターウォーズの、登場人物、映画、人種、宇宙船、自動車、惑星を紹介する

* 最小API Level:28
* ターゲットAPI Level:33

## 使用ライブラリ
|ライブラリ名|バージョン|用途|
|--|--|--|
| Retrofit2 | 2.9.0 | 通信ライブラリ |
| Timber | 5.0.1 | ログ出力 |
| Room | 2.4.3 | データベース |
| LiveData | 2.5.1 | コルーチン|
| Navigation | 2.9.0 | 画面遷移 |



## パッケージ構成
| パッケージ名 | 説明 |
|--|--|
| activity | Activity |
| adapter | RecyclerView の Adapter |
| api | API処理の基盤 |
| base | BaseFragment、BaseViewModel |
| data | データクラス、データベースのレスポンスなど |
| fragment | Fragment 各FragmentのViewModel |
| repository | APIのリクエスト、データベースの操作などを行う |
| utils | Enum、WebViewのリンク、Date型の処理など |
