(ns marketentry.facts
  "Per-jurisdiction public-procurement market-entry regulatory catalog
  -- the G2-style spec-basis table the Market-Entry Compliance Governor
  checks every `:jurisdiction/assess` proposal against ('did the advisor
  cite an OFFICIAL public source for this jurisdiction's requirements,
  or did it invent one?').

  Suriname (SUR) catalog -- ZERO-FABRICATION discipline: every fact
  below traces to a specific, independently-fetched-and-read official
  government source (all Dutch-language, Suriname being a former Dutch
  colony where Dutch remains the official language; verbatim Dutch is
  quoted in this docstring/README alongside an English gloss, the Dutch
  being primary).

  This iteration's own explicit finding (worth stating up front,
  mirroring the honest-thin-catalog discipline of
  `cloud-itonami-iso3166-gnb`): Suriname has **no dedicated
  'aanbestedingswet' (procurement act)** and **no dedicated
  procurement-regulator agency** comparable to Guyana's NPTA or
  Guinea-Bissau's ARCP. This was verified, not assumed: De Nationale
  Assemblee (DNA, Suriname's parliament, https://www.dna.sr/) publishes
  a complete, browsable index of enacted law under
  `/wetgeving/surinaamse-wetten/` in two categories -- 'Geldende
  teksten t/m 2005' (~140 laws, every title fetched and reviewed this
  session) and 'Wetten na 2005' (post-2005 enactments, also reviewed) --
  and NEITHER category contains any law titled with 'aanbesteding'.
  Instead, government procurement of works/supplies/transport
  ('werken', 'leverantien', 'transporten') is governed directly by
  Suriname's general public-finance law, the Comptabiliteitswet 1952,
  and its implementing decree, the Comptabiliteitsbesluit -- this is
  this catalog's flagship mechanism (see `marketentry.registry` /
  `marketentry.governor` for the independently-recomputable threshold
  logic it grounds).

  Verified facts (source URL + verbatim quote on each below; PDFs were
  fetched and their extracted text read directly, not paraphrased from
  training-data memory):

  - **Public procurement legal basis**: Comptabiliteitswet 1952 ('WET
    van 2 december 1952, regelende de wijze van beheer en verantwoording
    van de geldmiddelen in Suriname', G.B. 1952 no. 111, amended G.B.
    1955 no. 38 / G.B. 1969 no. 58 / S.B. 2003 no. 76). Its own Artikel
    18 reads, verbatim: 'Het uitvoeren van werken en het doen van
    leverantien en transporten ten behoeve van de Staat worden in het
    openbaar aanbesteed. Alleen wanneer de President zulks in het
    belang van de Staat nodig oordeelt, kan van die regel worden
    afgeweken.' (English gloss: 'The execution of works and the
    provision of supplies and transport for the State shall be
    publicly tendered. Only when the President judges this necessary
    in the interest of the State may this rule be deviated from.') Any
    deviation must state its reasons in the decision and a copy must be
    sent to Parliament (same article).
    Source: https://www.dna.sr/media/5ioj0qc3/comptabiliteitswet.pdf
  - **Public procurement implementing decree and the two numeric
    thresholds that ground this catalog's flagship check**: the
    Comptabiliteitsbesluit ('STAATSBESLUIT van 27 augustus 1953, tot
    vaststelling van algemene voorschriften ter uitvoering van de
    Comptabiliteitswet', G.B. 1953 no. 100, amended through S.B. 2004
    no. 116). Its own Artikel 18 (as amended) reads, verbatim:
    'Aanbestedingen ten behoeve van Staatsdienst voor werken, leveringen
    of transporten worden in het openbaar gehouden door of vanwege de
    betrokken minister, tenzij de President ... afwijking van deze
    regel in het belang van de Staat nodig oordeelt.' -- and then lists
    two exceptions to open tendering, the second being: 'voor
    leveringen, werkzaamheden en transporten tot geen hoger bedrag dan
    SRD.30.000,-- (DERTIGDUIZEND SURINAAMSE DOLLAR), met dien verstande
    dat de goedkeuring van de Raad van Ministers vereist is voor
    uitgaven een bedrag van SRD. 4.000,- (VIERDUIZEND SURINAAMSE
    DOLLAR) te boven gaande.' (English gloss: '...for supplies, works
    and transport not exceeding SRD 30,000, provided that Council of
    Ministers approval is required for expenditure exceeding SRD
    4,000.') This is a genuine three-tier, independently-recomputable
    rule from the government's own decree text: <= SRD 4,000 needs
    neither tender nor Council-of-Ministers approval; > SRD 4,000 and
    <= SRD 30,000 needs Council-of-Ministers (Raad van Ministers)
    approval but not a public tender; > SRD 30,000 MUST be an openbare
    aanbesteding (public tender) unless the President has granted an
    on-the-record exception.
    Source: https://www.dna.sr/media/hexjjg1r/comptabiliteitsbesluit.pdf
  - **No dedicated procurement regulator**: procurement is held
    decentralized, 'door of vanwege de betrokken minister' (by or on
    behalf of the responsible minister) per-department, with
    cross-departmental/general procurements held by or on behalf of the
    Minister of Finance (Ministerie van Financien) -- see the same
    Comptabiliteitsbesluit Artikel 18, second paragraph. The Ministry
    of Justice and Police (Ministerie van Justitie en Politie) also
    posts 'Openbare aanbestedingen' (public procurement) announcements
    in a 'Bekendmaking' section on the government portal.
    Source: https://www.gov.sr/
  - **Business/company registration**: the Kamer van Koophandel en
    Fabrieken (KKF) maintains the Handelsregister (Trade Register,
    confirmed public: 'Ja, het Handelsregister is openbaar'), legally
    grounded in the Handelsregisterwet 1936 (G.B. 1936 no. 149, geldende
    tekst G.B. 1962 no. 187, laatstelijk gewijzigd bij S.B. 1981 no.
    23) -- confirmed via a 1997 amending law's own 'Nota van
    Toelichting' (explanatory memorandum), which additionally confirms
    KKF's OWN separate institutional legal basis, verbatim: 'Op grond
    van artikel 30 lid 1 van de \"Wet Kamers van Koophandel 1962\"
    (G.B.1962 No.85), moeten de kosten van deze instelling onder andere
    bestreden worden uit gelden van vergoedingen voor het gebruik van
    bij haar in beheer zijnde inrichtingen en voor door haar bewezen
    diensten.' (English gloss: 'Under article 30(1) of the \"Chambers
    of Commerce Act 1962\" (G.B. 1962 No. 85), the costs of this
    institution [KKF] must be covered, among other things, from fees
    for facilities it manages and services it renders.') Registration
    is via KKF's own e-services portal (Naamgoedkeuring / name
    approval precedes Handelsregister entry; extract cost SRD 300 per
    KKF's own site).
    Sources: https://www.dna.sr/media/ae1hho5c/sb_1997_no_52_wet_handelsregisterwet.pdf ;
    https://www.surinamechamber.com/ (kkf.sr redirects here)
  - **Foreign/general investment**: the Investeringswet 2001 ('WET van
    3 juni 2002, houdende bepalingen ter stimulering van investeringen
    in Suriname (Investeringswet 2001)', S.B. 2002 no. 42) establishes
    fiscal (tax depreciation/deduction, import-duty and turnover-tax
    exemptions) and non-fiscal facilities for investment in specific
    sectors -- its own Artikel 3 reads, verbatim: 'Tenzij in deze wet
    anders is bepaald, komen voor de in deze wet genoemde faciliteiten
    in aanmerking investeringen in de sectoren landbouw, veeteelt,
    visserij, aquacultuur, mijnbouw, bosbouw, toerisme, met
    uitzondering van de inrichting en exploitatie van casino's,
    industrie, handel, bouwnijverheid, dienstverlening en
    beroepsvervoer.' (agriculture, animal husbandry, fisheries,
    aquaculture, mining, forestry, tourism EXCLUDING casino operation;
    industry, trade, construction, services and professional transport
    are excluded from this law's incentive scope). Requests are filed
    with **Investsur**, itself confirmed a real, currently-legislated
    body by the law's own Artikel 1(1)(b): 'Investsur: het in artikel
    16 lid 1 bedoelde Instituut ter bevordering van investeringen, dat
    is ingesteld bij wet van 3 juni 2002 (S.B. 2002 no.41).' (Investsur
    = the Institute for the Promotion of Investments, established by a
    companion law of the same date.) Artikel 16(1): 'Verzoeken om
    toepassing van faciliteiten uit deze wet worden door de ondernemer
    schriftelijk ingediend bij Investsur.' A live Investsur website was
    NOT independently reached this session (a guessed domain,
    investsur.sr, timed out on DNS resolution) -- its existence and
    legal basis are confirmed via the primary law text above, not via
    its own site; no live-site claim is made.
    Source: https://www.dna.sr/media/ggxkr25o/investeringswet.pdf
  - **Tax registration**: the Belastingdienst Suriname
    (belastingdienst.sr) operates a registration/filing portal
    ('portaal.belastingdienst.sr/OPO') covering, verbatim: 'de
    belastingmiddelen BTW, Loonbelasting en Premie AOV, en
    Inkomstenbelasting' (VAT, wage tax, old-age-provision premium, and
    income tax). The three underlying tax laws are confirmed via their
    OWN citations inside the Investeringswet 2001 text (Artikel 1(1)
    definitions): Inkomstenbelasting 1922 (G.B. 1921 no. 122, geldende
    tekst G.B. 1960 no. 84, laatstelijk gewijzigd bij S.B. 2000 no.
    123); Wet Loonbelasting (S.B. 1981 no. 181, geldende tekst S.B.
    1985 no. 10, laatstelijk gewijzigd bij S.B. 1994 no. 88); Wet
    Omzetbelasting 1997 (S.B. 1997 no. 87). NO specific unified
    taxpayer-ID/FIN scheme name (comparable to Guyana's GRA 'TIN') was
    independently confirmed on Belastingdienst's own fetched page
    content -- this catalog does NOT invent one; the tax-registration
    requirement below is grounded only in registration with the
    Belastingdienst via its own portal, honestly, not a named ID
    scheme.
    Source: https://belastingdienst.sr/
  - **Labour law** (for `statute.facts`, general-compliance catalog,
    not a governor check here): Arbeidswet 1963 ('WET van 19 december
    1963 tot vaststelling van bepalingen betreffende de arbeid', G.B.
    1963 no. 163, amended S.B. 1980 no. 116 / S.B. 1983 no. 91 / S.B.
    2001 no. 71). Its own Artikel 3 lid 1 reads, verbatim: 'Het is
    verboden in een onderneming langer arbeid te verrichten of door een
    werknemer te doen verrichten dan 8 1/2 uur per dag of 48 uren per
    week.' (max 8.5 hours/day or 48 hours/week).
    Source: https://www.dna.sr/media/cwabf0np/arbeidswet.pdf

  Explicitly NOT claimed (fabrication traps deliberately avoided):

  - No 'aanbestedingswet' / dedicated procurement act -- exhaustively
    checked against DNA's own complete law index (both pre-2005 and
    post-2005 categories); none exists. Do not invent one.
  - No dedicated procurement-regulator agency/acronym (no Surinamese
    analog of NPTA/ARCP was found) -- procurement authority is
    decentralized per-ministry under the Comptabiliteitsbesluit; do not
    invent an agency name.
  - No national e-procurement portal was found -- none is invented;
    'Openbare aanbestedingen' are announced on the general gov.sr
    portal, not a dedicated transactional e-procurement system.
  - No specific unified taxpayer-ID/FIN number scheme name for
    Belastingdienst registration was found -- do not invent a 'TIN'-like
    acronym for Suriname.
  - No local-content or sector-ownership law (comparable to Guyana's
    Local Content Act 2021) was found or searched exhaustively enough
    to claim one way or the other for Suriname -- none is included
    here; this is an honest coverage gap, not an asserted absence.
  - Investsur's own live website was not independently confirmed
    reachable this session (DNS timeout on a guessed domain) -- its
    existence/legal basis rests on the Investeringswet 2001's own text,
    not a self-asserted website claim.

  Coverage is reported HONESTLY (see `coverage`): a jurisdiction not in
  this table has NO spec-basis, full stop -- the advisor must not
  fabricate one, and the governor holds if it tries.")

(def catalog
  "iso3 -> requirement map. `:required-evidence` mirrors the generic
  intake/portal-registration/filing evidence set; `:legal-basis` /
  `:owner-authority` / `:provenance` are the G2 citation the governor
  requires before any `:jurisdiction/assess` proposal can commit.

  For SUR: `:owner-authority` honestly states there is NO dedicated
  procurement regulator (decentralized per-ministry under the
  Comptabiliteitsbesluit); `:business-registration-*` is KKF (Wet
  Kamers van Koophandel 1962 + Handelsregisterwet 1936, a DIFFERENT
  legal basis from the procurement rules); `:corporate-number-*` is
  Belastingdienst Suriname (general tax registration, no named ID
  scheme); `:procurement-threshold-*` carries the two independently-
  recomputable SRD thresholds (4,000 / 30,000) that ground
  `marketentry.registry/procurement-threshold-noncompliant?` -- the
  FLAGSHIP check for this jurisdiction, genuinely different in shape
  from every sibling actor's own flagship (a numeric three-tier
  threshold recompute, not a trigger-set or a boolean flag). There is
  NO `:local-content-*` sub-map for SUR -- no such law was found, and
  none is invented (contrast Guyana's Local Content Act 2021)."
  {"SUR" {:name "Suriname"
          :owner-authority "No dedicated procurement-regulator agency exists for Suriname (verified absence: neither of De Nationale Assemblee's own complete law-index categories, 'Geldende teksten t/m 2005' or 'Wetten na 2005', contains any law titled with 'aanbesteding'); procurement of works/supplies/transport for the State is held decentralized, by or on behalf of the responsible minister per department, with cross-departmental procurements held by or on behalf of the Minister of Finance (Ministerie van Financien), under the Comptabiliteitsbesluit"
          :legal-basis "Comptabiliteitswet 1952 (G.B. 1952 no. 111, amended G.B. 1955 no. 38 / G.B. 1969 no. 58 / S.B. 2003 no. 76) Artikel 18 (mandates public tendering of works/supplies/transport for the State, deviation only by presidential decision with recorded reasons + copy to Parliament); implementing decree Comptabiliteitsbesluit (G.B. 1953 no. 100, amended through S.B. 2004 no. 116) Artikel 18 (restates the public-tender rule and adds the SRD 4,000 / SRD 30,000 threshold tiers)"
          :national-spec "Openbare aanbesteding (public tender) required for State works/supplies/transport contracts above SRD 30,000 (deviation only by presidential exception, on the record); contracts above SRD 4,000 and up to SRD 30,000 may be procured without tender but require Raad van Ministers (Council of Ministers) approval; contracts of SRD 4,000 or less require neither"
          :provenance "https://www.dna.sr/media/5ioj0qc3/comptabiliteitswet.pdf ; https://www.dna.sr/media/hexjjg1r/comptabiliteitsbesluit.pdf"
          :required-evidence ["Kamer van Koophandel en Fabrieken (KKF) Handelsregister extract"
                              "Belastingdienst Suriname tax-registration record (Inkomstenbelasting / Loonbelasting / Omzetbelasting, via portaal.belastingdienst.sr)"
                              "Contract-value / procurement-path classification record (SRD amount + openbare-aanbesteding vs. onderhandse-gunning path, per Comptabiliteitsbesluit Artikel 18)"
                              "Authorized-representative record"]
          :corporate-number-owner-authority "Belastingdienst Suriname"
          :corporate-number-legal-basis "Inkomstenbelasting 1922 (G.B. 1921 no. 122, geldende tekst G.B. 1960 no. 84, laatstelijk gewijzigd bij S.B. 2000 no. 123); Wet Loonbelasting (S.B. 1981 no. 181, geldende tekst S.B. 1985 no. 10, laatstelijk gewijzigd bij S.B. 1994 no. 88); Wet Omzetbelasting 1997 (S.B. 1997 no. 87) -- registration via portaal.belastingdienst.sr/OPO. No specific unified taxpayer-ID/FIN scheme name was independently confirmed; this is an honest gap, not a fabricated acronym."
          :corporate-number-provenance "https://belastingdienst.sr/"
          :business-registration-owner-authority "Kamer van Koophandel en Fabrieken (KKF)"
          :business-registration-legal-basis "Wet Kamers van Koophandel 1962 (G.B. 1962 no. 85, establishes KKF, confirmed via a 1997 amending law's own Nota van Toelichting) + Handelsregisterwet 1936 (G.B. 1936 no. 149, geldende tekst G.B. 1962 no. 187, laatstelijk gewijzigd bij S.B. 1981 no. 23) -- KKF maintains the public Handelsregister (Trade Register); registration via KKF's own e-services portal, Naamgoedkeuring (name approval) precedes entry"
          :business-registration-provenance "https://www.dna.sr/media/ae1hho5c/sb_1997_no_52_wet_handelsregisterwet.pdf ; https://www.surinamechamber.com/"
          :procurement-threshold-legal-basis "Comptabiliteitsbesluit (G.B. 1953 no. 100, amended through S.B. 2004 no. 116) Artikel 18: 'voor leveringen, werkzaamheden en transporten tot geen hoger bedrag dan SRD.30.000,-- ... met dien verstande dat de goedkeuring van de Raad van Ministers vereist is voor uitgaven een bedrag van SRD. 4.000,- te boven gaande'"
          :procurement-threshold-public-tender-srd 30000
          :procurement-threshold-council-approval-srd 4000
          :procurement-threshold-provenance "https://www.dna.sr/media/hexjjg1r/comptabiliteitsbesluit.pdf"}
   "USA" {:name "United States"
          :owner-authority "U.S. General Services Administration (GSA) / SAM.gov"
          :legal-basis "Federal Acquisition Regulation (FAR); System for Award Management"
          :national-spec "SAM.gov entity registration + NAICS self-certification"
          :provenance "https://sam.gov/"
          :required-evidence ["EIN record"
                              "SAM.gov registration record"
                              "State business registration record"
                              "Authorized-representative record"]}})

(defn spec-basis
  "The jurisdiction's requirement map, or nil -- nil means NO spec-basis,
  and the governor must hold any proposal that tries to assess or file
  on it."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report: how many of the requested jurisdictions actually
  have a spec-basis entry. Never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-sur R0: " (count catalog)
                 " jurisdictions seeded with an official spec-basis. "
                 "This is a starting catalog for market-entry navigation, "
                 "not a survey of all ~194 jurisdictions -- extend "
                 "`marketentry.facts/catalog`, never fabricate a "
                 "jurisdiction's requirements.")})))

(defn required-evidence-satisfied?
  "Does `submitted` (a set/coll of evidence keywords or strings) satisfy
  every evidence item listed for `iso3`? Missing spec-basis -> never
  satisfied."
  [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (let [need (count required-evidence)
          have (count (filter (set submitted) required-evidence))]
      (= need have))))

(defn evidence-checklist [iso3]
  (:required-evidence (spec-basis iso3) []))

(defn corporate-number-spec-basis
  "The jurisdiction's corporate-number / tax-registration regime
  (Belastingdienst Suriname, for SUR), or nil."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority
                       :corporate-number-legal-basis
                       :corporate-number-provenance]))))

(defn business-registration-spec-basis
  "The jurisdiction's business/company (state) registration regime, or
  nil. Suriname's business-registration authority is KKF -- a DIFFERENT
  body from `:corporate-number-spec-basis` (Belastingdienst, tax) and
  from the (non-existent, decentralized) procurement authority in
  `:owner-authority`."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:business-registration-owner-authority sb)
      (select-keys sb [:business-registration-owner-authority
                       :business-registration-legal-basis
                       :business-registration-provenance]))))

(defn procurement-threshold-spec-basis
  "The jurisdiction's procurement-threshold regime (Comptabiliteitsbesluit
  Artikel 18's SRD 4,000 / SRD 30,000 tiers, for SUR), or nil. This
  grounds the FLAGSHIP governor check for this jurisdiction --
  `marketentry.registry/procurement-threshold-noncompliant?` -- a
  numeric three-tier threshold recompute, genuinely different in shape
  from every sibling actor's own flagship check."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:procurement-threshold-legal-basis sb)
      (select-keys sb [:procurement-threshold-legal-basis
                       :procurement-threshold-public-tender-srd
                       :procurement-threshold-council-approval-srd
                       :procurement-threshold-provenance]))))
