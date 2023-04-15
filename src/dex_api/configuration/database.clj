(ns dex-api.configuration.database
  (:require [next.jdbc :as jdbc]
            [next.jdbc.connection :as connection]
            [next.jdbc.result-set :as result-set])
  (:import (com.zaxxer.hikari HikariDataSource)))

(defn connection-pool
  [configuration]
  (jdbc/with-options
    (connection/->pool HikariDataSource configuration)
    {:builder-fn result-set/as-unqualified-lower-maps}))
