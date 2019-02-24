(ns foursquare-excercise.core
  (:require [org.httpkit.client :as http])
    (:gen-class))

(def ^:private app-config {:client_id "OZE3X22Y4Z41PHWBUAFRU1RIUVS4Q3WJCXC1ZEDLNQOMBJ1I"
                           :client_secret "EYCJ1GNSXPLD4GHRUVLDTIIK5MCB5WAV1OJZQGH1ABHY3UAN"})

(def default-opts
  (merge app-config
         {:intent "global"
          :v "20190224"}))

(defn- get-recomendetions
  [input]
  (let [
        searched-place (http/get "https://api.foursquare.com/v2/venues/search"
                                 {:query-params (merge default-opts
                                                       {:query input})})]
    (println "Result " @searched-place)
    @searched-place))


(defn -main
  "I don't do a whole lot."
  [& args]
  (println "// ----- Welcome to Foursquare API excercise ----- \\\\")
  (println "Please input a place to search or type 'exit' to stop the app")
  (loop [*in* (read-line)]
        (if (= "exit" *in*)
          (println "good bye")
          (do
            (get-recomendetions *in*)
            (recur (read-line))))))