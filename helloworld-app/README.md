# helloworld-app

A very simple completely client-side pedestal application. It displays a
counter that increments itself every 3 seconds.

You may start the application by running the following commands:

```
lein repl
(dev)
(run)
```

Once it is started, browse to [http://localhost:3000/helloworld-app-dev.html](http://localhost:3000/helloworld-app-dev.html).

## How does it work?

The code and the html templates are in the [app](./app) directory. In
[app/src/helloworld_app/app.cljs](./app/src/helloworld_app/app.cljs) you
find all of the ClojureScript code.

For prettier documentation run `lein marg` (with [lein-marginalia set
up](https://github.com/fogus/lein-marginalia/#installation)) and look at
`docs/uberdoc.html`.

## Links

* [Pedestal Documentation](http://pedestal.io/documentation/)
* [Pedestal Samples](http://pedestal.io/#sample)

License
-------
Copyright 2013 Relevance, Inc.

The use and distribution terms for this software are covered by the
Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0)
which can be found in the file epl-v10.html at the root of this distribution.

By using this software in any fashion, you are agreeing to be bound by
the terms of this license.

You must not remove this notice, or any other, from this software.
