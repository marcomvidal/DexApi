(ns dex-api.people.repository
  (:require [aero.core :refer [read-config]]
            [dex-api.configuration.database :refer [connection-pool]]
            [dex-api.configuration.schemas :as schemas]
            [honey.sql :as sql]
            [next.jdbc :as jdbc]
            [schema.core :as s]))

(s/defn save! :- schemas/NextJdbcExecuteOperation
  [{:keys [connection]} person :- schemas/Person]
  (let [{:keys [name email birthdate]} person]
    (->> {:insert-into [:person]
          :columns [:name :email :birthdate]
          :values [[name email birthdate]]}
         sql/format
         (jdbc/execute! connection))))

(s/defn get-by-email! :- schemas/Person
  [{:keys [connection]} email :- s/Str]
  (->> {:select [:*]
        :from [:person]
        :where [:= :email email]
        :limit [1]}
       sql/format
       (jdbc/execute! connection)
       first))

(s/defn delete-by-email! :- schemas/NextJdbcExecuteOperation
  [{:keys [connection]} email :- s/Str]
  (->> {:delete-from [:person]
        :where [:= :email email]}
       sql/format
       (jdbc/execute! connection)))

(s/defn get-all! :- [schemas/Person]
  [{:keys [connection]}]
  (->> {:select [:*]
        :from [:person]}
       sql/format
       (jdbc/execute! connection)))

(comment
  (let [config (:database (read-config "resources/config.edn"))
        connection {:connection (connection-pool config)}]
    (get-by-email! connection "a@a.com")))
