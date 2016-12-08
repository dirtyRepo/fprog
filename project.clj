(defproject ads_board "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"

  :plugins [[lein-ring "0.9.7"]
            [lein-localrepo "0.5.3"]]
            
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [org.clojure/java.jdbc "0.3.0-alpha5"]
                 [mysql/mysql-connector-java "5.1.25"]
                 [ring/ring-json "0.4.0"]

                 [selmer "1.0.9"]
                 [markdown-clj "0.9.89"]
                 [metosin/ring-http-response "0.8.0"]
                 [bouncer "1.0.0"]
                 [org.webjars/bootstrap "4.0.0-alpha.3"]
                 [org.webjars/font-awesome "4.6.3"]
                 [org.webjars.bower/tether "1.3.7"]
                 [org.clojure/tools.logging "0.3.1"]
                 [hiccup-bootstrap "0.1.2"]]
  
  :ring {:handler ads-board.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
