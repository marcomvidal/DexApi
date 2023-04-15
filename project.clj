

(defproject dex-api "0.0.1-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [io.pedestal/pedestal.service "0.5.11-beta-1"]

                 [io.pedestal/pedestal.jetty "0.5.11-beta-1"]
                 ;; [io.pedestal/pedestal.immutant "0.5.11-beta-1"]
                 ;; [io.pedestal/pedestal.tomcat "0.5.11-beta-1"]

                 [ch.qos.logback/logback-classic "1.2.10" :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/jul-to-slf4j "1.7.35"]
                 [org.slf4j/jcl-over-slf4j "1.7.35"]
                 [org.slf4j/log4j-over-slf4j "1.7.35"]
                 [com.github.seancorfield/next.jdbc "1.3.847"]
                 [org.postgresql/postgresql "42.2.10"]
                 [com.zaxxer/HikariCP "3.3.1"]
                 [com.github.seancorfield/honeysql "2.4.962"]
                 [aero "1.1.6"]
                 [clj-time "0.15.2"]
                 [prismatic/schema "1.4.1"]
                 [metosin/reitit "0.5.18"]
                 [metosin/reitit-pedestal "0.5.18"] 
                 [metosin/muuntaja "0.6.8"]
                 [com.stuartsierra/component "1.1.0"]
                 [metosin/reitit-swagger "0.6.0"]
                 [metosin/reitit-swagger-ui "0.6.0"]]
  :min-lein-version "2.0.0"
  :resource-paths ["config", "resources"]
  ;; If you use HTTP/2 or ALPN, use the java-agent to pull in the correct alpn-boot dependency
  ;:java-agents [[org.mortbay.jetty.alpn/jetty-alpn-agent "2.0.5"]]
  :profiles {:dev {:aliases {"run-dev" ["trampoline" "run" "-m" "dex-api.server/run-dev"]}
                   :dependencies [[io.pedestal/pedestal.service-tools "0.5.11-beta-1"]]}
             :uberjar {:aot [dex-api.server]}}
  :main ^{:skip-aot true} dex-api.server)
