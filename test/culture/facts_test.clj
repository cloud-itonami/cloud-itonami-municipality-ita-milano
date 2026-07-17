(ns culture.facts-test
  (:require [clojure.edn :as edn]
            [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [culture.facts :as facts]))

(deftest milano-has-culture-basis
  (let [sb (facts/spec-basis "milano")]
    (is (= 9 (count sb)))
    (is (= (count sb) (count (set (map :culture/id sb)))))
    (is (every? #(str/starts-with? (:culture/url %) "https://") sb))
    (is (every? #(= "milano" (:culture/municipality %)) sb))
    (is (every? #(= "ITA" (:culture/country %)) sb))
    (is (every? #(seq (:culture/summary %)) sb))
    (is (every? #(string? (:culture/retrieved-at %)) sb))))

(deftest unknown-municipality-has-no-basis
  (is (nil? (facts/spec-basis "roma")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["milano" "roma"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["roma"] (:missing-municipalities c)))))

(deftest by-kind-filters
  (is (= 5 (count (facts/by-kind "milano" :dish))))
  (is (= ["milano.beverage.barbajada"]
         (mapv :culture/id (facts/by-kind "milano" :beverage))))
  (is (empty? (facts/by-kind "milano" :craft)))
  (is (empty? (facts/by-kind "roma" :dish))))

(deftest tx-file-matches-catalog
  (let [tx (edn/read-string (slurp "data/culture-tx.edn"))
        flat (mapcat val (sort-by key facts/catalog))]
    (is (= (vec flat) (vec tx)))))
