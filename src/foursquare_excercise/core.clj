(ns foursquare-excercise.core
  (:require [org.httpkit.client :as http]
            [clojure.data.json :as json])
    (:gen-class))

(def ^:private app-config {:client_id "OZE3X22Y4Z41PHWBUAFRU1RIUVS4Q3WJCXC1ZEDLNQOMBJ1I"
                           :client_secret "EYCJ1GNSXPLD4GHRUVLDTIIK5MCB5WAV1OJZQGH1ABHY3UAN"
                           :v "20190224"})

(def default-opts
  (merge app-config
         {:intent "global"}))

(defn- get-searched-venue
  [input]
  (let [searched-place (http/get "https://api.foursquare.com/v2/venues/search"
                                 {:query-params (merge default-opts
                                                       {:query input})})]
    (-> @searched-place :body (json/read-str :key-fn keyword) :response :venues first)))

(defn- get-recomended-venues
  [{:keys [location]}]
  (let [api-resp (http/get "https://api.foursquare.com/v2/venues/search"
                           {:query-params (merge app-config
                                                 {:ll (str (:lat location) "," (:lng location))})})]
    (println "Api resp for recomendations <<<<<<")
    (clojure.pprint/pprint (-> @api-resp :body (json/read-str :key-fn keyword) :response :venues))
    api-resp))

(defn- get-recomendetions
  [input]
  (let [searched-place (get-searched-venue input)]
    (println "Searched place >>>>>")
    (clojure.pprint/pprint searched-place)
    (get-recomended-venues searched-place)
    searched-place))


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