(ns marketentry.governor
  "Market-Entry Compliance Governor -- the independent compliance layer
  that earns the MarketEntry-LLM the right to commit. The LLM has no
  notion of Surinamese public-finance law, whether a claimed
  Handelsregister (KKF) or Belastingdienst tax registration is actually
  on file, whether a claimed contract value actually requires an
  openbare aanbesteding (public tender) or Raad van Ministers approval
  under the Comptabiliteitsbesluit, whether a claimed engagement fee
  actually equals base + months x rate, or when a draft stops being a
  draft and becomes a real-world filing submission, so this MUST be a
  separate system able to *reject* a proposal and fall back to HOLD.

  `:itonami.blueprint/governor` is `:market-entry-compliance-governor`
  (shared family keyword on blueprints).

  This blueprint's own text (docs/business-model.md Trust Controls:
  'any actual filing submission requires Market-Entry Compliance
  Governor clearance and always escalates to human sign-off'; 'a false
  or fabricated regulatory-requirement claim is a HARD hold') names
  exactly the checks below.

  FIVE hard-violation check functions (items 1-5), ALL HARD violations:
  a human approver CANNOT override them. This is fewer than some
  sibling actors' seven -- honestly: Suriname's own verified fact set
  (see `marketentry.facts`) gives no confirmed sector-conditional
  local-content law (unlike Guyana's Local Content Act 2021) to ground
  a sixth/seventh check, and no confirmed non-resident-registration
  trigger set (unlike Guyana's Companies Act 1991) -- business
  registration is instead folded into the universal evidence-incomplete
  checklist rather than force-fit into a fabricated conditional check.
  The genuinely NEW, well-grounded check this jurisdiction adds is item
  3, `procurement-threshold-noncompliant` -- a numeric three-tier
  threshold recompute, directly grounded in the Comptabiliteitsbesluit's
  own text, and a different SHAPE from every sibling's own flagship.
  Item 6 (confidence/actuation gate) is SOFT: it asks a human to look
  (low confidence / actuation), and the human may approve -- but see
  `marketentry.phase`: for `:stake :actuation/draft-filing`/
  `:actuation/submit-filing` NO phase ever allows auto-commit either.
  Two independent layers agree that actuation is always a human call.

    1. Spec-basis                  -- did the jurisdiction proposal cite
                                       an OFFICIAL source
                                       (`marketentry.facts`), or invent
                                       one?
    2. Evidence incomplete         -- for `:filing/draft`/
                                       `:filing/submit`, has the
                                       jurisdiction actually been
                                       assessed with a full evidence
                                       checklist on file (KKF
                                       Handelsregister record,
                                       Belastingdienst tax-registration
                                       record, contract-value/
                                       procurement-path classification
                                       record, authorized-representative
                                       record)?
    3. Procurement threshold
       noncompliant                  -- for `:filing/submit`,
                                       INDEPENDENTLY recompute, from the
                                       engagement's own
                                       `:contract-value-srd`, whether
                                       its claimed `:procurement-path`
                                       (and any presidential exception /
                                       Council-of-Ministers approval on
                                       file) actually satisfies
                                       Comptabiliteitsbesluit Artikel
                                       18's SRD 4,000 / SRD 30,000
                                       tiers. FLAGSHIP check for this
                                       jurisdiction -- see
                                       `marketentry.registry`.
    4. Engagement fee mismatch     -- for `:filing/submit`,
                                       INDEPENDENTLY recompute whether
                                       the engagement's own `:claimed-
                                       fee` equals `base-fee +
                                       monthly-rate x monitoring-
                                       months` -- honest reapplication
                                       of the ground-truth-recompute
                                       discipline sibling actors use.
    5. Tax registration unconfirmed -- for `:filing/submit`,
                                       INDEPENDENTLY check
                                       `:tax-registration-verified?`
                                       (Belastingdienst registration
                                       under Inkomstenbelasting
                                       1922/Wet Loonbelasting/Wet
                                       Omzetbelasting 1997) --
                                       UNCONDITIONAL, grounded in the
                                       general tax-compliance baseline
                                       every operating enterprise in
                                       Suriname is subject to (no
                                       Suriname-specific 'government
                                       contractors must register'
                                       clause was independently found,
                                       unlike Guyana's GRA guidance --
                                       this check is honestly scoped to
                                       the general registration
                                       requirement, not a fabricated
                                       procurement-specific mandate).
    6. Confidence floor / actuation
       gate                          -- LLM confidence below threshold,
                                       OR the op is `:filing/draft`/
                                       `:filing/submit` (REAL acts)
                                       -> escalate.

  Two more guards, double-draft/double-submit prevention, are enforced
  off dedicated `:drafted?`/`:submitted?` facts (never a `:status`
  value)."
  (:require [marketentry.facts :as facts]
            [marketentry.registry :as registry]
            [marketentry.store :as store]))

(def confidence-floor 0.6)

(def high-stakes
  "Stakes grave enough to always require a human, even when clean.
  Drafting a real filing package and submitting a real filing are the
  two real-world actuation events this actor performs."
  #{:actuation/draft-filing :actuation/submit-filing})

;; ----------------------------- checks -----------------------------

(defn- spec-basis-violations
  "A `:jurisdiction/assess` (or `:filing/draft`/`:filing/submit`)
  proposal with no spec-basis citation is a HARD violation -- never
  invent a jurisdiction's market-entry requirements."
  [{:keys [op]} proposal]
  (when (contains? #{:jurisdiction/assess :filing/draft :filing/submit} op)
    (let [value (:value proposal)]
      (when (or (empty? (:cites proposal))
                (and (contains? value :spec-basis) (nil? (:spec-basis value))))
        [{:rule :no-spec-basis
          :detail "公式spec-basisの引用が無い提案は法域要件として扱えない"}]))))

(defn- evidence-incomplete-violations
  "For `:filing/draft`/`:filing/submit`, the jurisdiction's required
  registration evidence must actually be satisfied."
  [{:keys [op subject]} st]
  (when (contains? #{:filing/draft :filing/submit} op)
    (let [e (store/engagement st subject)
          assessment (store/assessment-of st subject)]
      (when-not (and assessment
                     (facts/required-evidence-satisfied?
                      (:jurisdiction e) (:checklist assessment)))
        [{:rule :evidence-incomplete
          :detail "法域の必要書類(KKF商業登記/Belastingdienst税務登録/契約金額・調達経路分類記録/代理人確認等)が充足していない状態での提案"}]))))

(defn- procurement-threshold-noncompliant-violations
  "For `:filing/submit`, INDEPENDENTLY recompute whether the
  engagement's own claimed procurement path satisfies the
  Comptabiliteitsbesluit Artikel 18 SRD 4,000 / SRD 30,000 tiers.
  FLAGSHIP check for this jurisdiction."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when (registry/procurement-threshold-noncompliant? e)
        [{:rule :procurement-threshold-noncompliant
          :detail (str subject " の契約金額(SRD " (:contract-value-srd e)
                      ")は Comptabiliteitsbesluit Artikel 18 の閾値(SRD 30,000超は"
                      "openbare aanbesteding必須、SRD 4,000超はRaad van Ministers承認必須)"
                      "を満たしていない -- 提出提案は進められない")}]))))

(defn- engagement-fee-mismatch-violations
  "For `:filing/submit`, INDEPENDENTLY recompute whether the
  engagement's own claimed fee equals base + months x rate."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when-not (registry/engagement-fee-matches-claim? e)
        [{:rule :engagement-fee-mismatch
          :detail (str subject " の申告手数料(" (:claimed-fee e)
                      ")が独立再計算値(" (registry/compute-engagement-fee e) ")と一致しない")}]))))

(defn- tax-registration-unconfirmed-violations
  "For `:filing/submit`, INDEPENDENTLY check
  `:tax-registration-verified?`, UNCONDITIONALLY -- grounded in the
  general Belastingdienst registration baseline (Inkomstenbelasting
  1922 / Wet Loonbelasting / Wet Omzetbelasting 1997), honestly scoped
  (no procurement-specific mandate was independently confirmed for
  Suriname)."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when-not (true? (:tax-registration-verified? e))
        [{:rule :tax-registration-unconfirmed
          :detail (str subject " は Belastingdienst Suriname の税務登録"
                      "(Inkomstenbelasting/Loonbelasting/Omzetbelasting)確認が未完了 -- "
                      "提出提案は進められない")}]))))

(defn- already-drafted-violations
  "For `:filing/draft`, refuses to draft the SAME engagement twice."
  [{:keys [op subject]} st]
  (when (= op :filing/draft)
    (when (store/engagement-already-drafted? st subject)
      [{:rule :already-drafted
        :detail (str subject " は既にドラフト済み")}])))

(defn- already-submitted-violations
  "For `:filing/submit`, refuses to submit the SAME engagement twice."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (when (store/engagement-already-submitted? st subject)
      [{:rule :already-submitted
        :detail (str subject " は既に提出済み")}])))

(defn check
  "Censors a MarketEntry-LLM proposal against the governor rules.
  Returns {:ok? bool :violations [..] :confidence c :escalate? bool
  :high-stakes? bool :hard? bool}."
  [request _context proposal st]
  (let [hard (into []
                   (concat (spec-basis-violations request proposal)
                           (evidence-incomplete-violations request st)
                           (procurement-threshold-noncompliant-violations request st)
                           (engagement-fee-mismatch-violations request st)
                           (tax-registration-unconfirmed-violations request st)
                           (already-drafted-violations request st)
                           (already-submitted-violations request st)))
        conf (:confidence proposal 0.0)
        low? (< conf confidence-floor)
        stakes? (boolean (high-stakes (:stake proposal)))
        hard? (boolean (seq hard))]
    {:ok?          (and (not hard?) (not low?) (not stakes?))
     :violations   hard
     :confidence   conf
     :hard?        hard?
     :escalate?    (and (not hard?) (or low? stakes?))
     :high-stakes? stakes?}))

(defn hold-fact
  "The audit fact written when a proposal is rejected (HOLD)."
  [request context verdict]
  {:t          :governor-hold
   :op         (:op request)
   :actor      (:actor-id context)
   :subject    (:subject request)
   :disposition :hold
   :basis      (mapv :rule (:violations verdict))
   :violations (:violations verdict)
   :confidence (:confidence verdict)})
