; Copyright 2013 Relevance, Inc.

; The use and distribution terms for this software are covered by the
; Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0)
; which can be found in the file epl-v10.html at the root of this distribution.
;
; By using this software in any fashion, you are agreeing to be bound by
; the terms of this license.
;
; You must not remove this notice, or any other, from this software.

(ns helloworld-app.app
  "A simple pedestal application that increments a counter every 3 seconds."
  (:require [io.pedestal.app :as app]
            [io.pedestal.app.protocols :as p]
            [io.pedestal.app.render :as render]
            [io.pedestal.app.render.push :as push]
            [io.pedestal.app.messages :as msg]
            [domina :as dom]))

(defn count-model
  "Updates the state of a counter.

You can consider it `inc` in terms of pedestal. For example:

    (count-model nil {msg/type msg/init, :value 0})
    ;=> 0
    (count-model 0 {msg/type :inc})
    ;=> 1"
  [old-state message]
  (condp = (msg/type message)
    msg/init (:value message)
    :inc (inc old-state)))

(defn render-value
  "Renders the new state of the counter.

Note that this function will be set up in a way that it will only
receive the new counter values, if you have more than one type of
state, then you would render it separately."
  [r [_ _ old-value new-value] input-queue]
  (dom/destroy-children! (dom/by-id "content"))
  (dom/append! (dom/by-id "content")
               (str "<h1>" new-value " Hello Worlds</h1>")))

(def count-app
  "Defines the count application.

In our case, this is a single transform `:count`, which will be
initialized to `0` and updated using `count-model`."
  {:transform {:count {:init 0 :fn count-model}}})

(defn receive-input
  "Generates `:inc` values periodically.

Here you see that `:count` is a topic, that is kind-of namespaced state.
You could also generate them differently, e.g. on the click of a button
or depending on other state changes."
  [input-queue]
  (p/put-message input-queue {msg/topic :count msg/type :inc})
  (.setTimeout js/window #(receive-input input-queue) 3000))

(defn ^:export main
  "Starts the action. Have fun!

This \"puts the application together\", creates a renderer, sets up a
source of `:inc` events for the counter and then starts the fun."
  []
  (let [app (app/build count-app)
        render-fn (push/renderer "content" [[:value [:**] render-value]])]
    (render/consume-app-model app render-fn)
    ; Call once, it sets up a timeout to call itself every 3 seconds
    (receive-input (:input app))
    (app/begin app)))

