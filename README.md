# User Tracking app
* Architecture: MVVM with databinding
* Some libraries: Retrofit, Dagger2, RxJava2, Google Architecture Components(Room, ViewModel)
* Application provides example on how we can load data in offline-first style
    * It will first check data from database
        * If data available in database, we display it
        * If not, it will call the network to get new data, display it and save to database
    * We can also click refresh to force update data from network

## Screenshots
<p align="center">
  <img src="/screenshots/screenshot_1.jpg" width="350" alt="accessibility text">
  <img src="/screenshots/Screenshot_2.jpg" width="350" alt="accessibility text">
</p>

## How to Contribute

Find any typos? Any ideas that you think they can help to improve the project? Contributions are welcome!

First, fork this repository.

[Clone this repository in github for windows](github-windows://openRepo/https://github.com/theanh0512/UserTracking)

Or clone this repository from command to make changes.

```sh
$ git clone git@github.com:theanh0512/UserTracking.git
$ cd UserTracking
```

Once you've pushed changes to your local repository, you can issue a pull request by clicking on the green pull request icon.

## License

The contents of this repository are covered under the [Apache License 2.0](LICENSE).
