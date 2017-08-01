# zapier

Project with some test of zapier homepage. Checking if elements are displayed and in case of logo,
making a screenshot of logo element and comparing with stored one.

## Prerequisites

Java, gradle, Chrome browser, Firefox browser

Optional: Docker


## Running the tests

### For local run

In project root run command (depending on which browser you want):
```
gradle chrome_local
```
or
```
gradle firefox_local
```

### Using docker and selenium grid

In project root run commands:
```
gradle startGrid
gradle scaleUpGrid
gradle chrome
gradle firefox
```

to shut down docker containers

```
gradle stopGrid
```

## Additional information

Because elements are displayed in slighlty diffrent manner on different browsers, example screenshot
of logo is stored for each browser type. Additionally, docker containers have different resolution then
typical desktop. Because of that, I stored additional image for resolution 1440:900. Test should also
work on different resolutions (screenshot should be taken correctly) but it do not have example to compare to...
