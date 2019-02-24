(ns foursquare-excercise.core
    (:gen-class))

(defn -main
  "I don't do a whole lot."
  [& args]
  (println "// ----- Welcome to Foursquare API excercise ----- \\\\")
  (println "Please input a place to search or type 'exit' to stop the app")
  (loop [*in* (read-line)]
        (println "this is my input: " *in*)
        (recur (read-line))))