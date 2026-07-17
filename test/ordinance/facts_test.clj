(ns ordinance.facts-test
  (:require [clojure.edn :as edn]
            [clojure.test :refer [deftest is]]
            [ordinance.facts :as facts]))

(deftest milano-has-spec-basis
  (let [sb (facts/spec-basis "milano")]
    (is (= 2 (count sb)))
    (is (every? #(re-find #"^https://(www|www2)\.comune\.milano\.it/" (:ordinance/url %)) sb))))

(deftest unknown-municipality-has-no-spec-basis
  (is (nil? (facts/spec-basis "roma")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["milano" "roma"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["roma"] (:missing-municipalities c)))))

(deftest by-topic-filters
  (is (= ["milano.regolamento-benessere-tutela-animali-2020"]
         (mapv :ordinance/id (facts/by-topic "milano" :animal-welfare))))
  (is (empty? (facts/by-topic "milano" :labor)))
  (is (empty? (facts/by-topic "roma" :building))))

(deftest tx-file-matches-catalog
  (let [tx (edn/read-string (slurp "data/datascript-tx.edn"))
        flat (mapcat val (sort-by key facts/catalog))]
    (is (= (vec flat) (vec tx)))))
