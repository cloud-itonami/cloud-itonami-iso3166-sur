# cloud-itonami-iso3166-sur

Open ISO 3166 Blueprint for **SUR**: Republic of Suriname --
**`:implemented`**.

This repository designs **and implements** a forkable OSS business for
an independent public-sector market-entry consultant: an already-
incorporated operator (e.g. a `cloud-itonami-cofog-{code}`,
`cloud-itonami-isco-{code}`, `cloud-itonami-unspsc-{segment}` or
`cloud-itonami-{ISIC}` blueprint fork) gets a Compliance Advisor +
independent **Market-Entry Compliance Governor** to navigate public-
procurement, local business/tax registration, and regulatory-compliance
rules in Suriname, so the operator can win and service a government
contract without hiring a full in-house compliance department.

Suriname is a former Dutch colony where Dutch remains the official
language; every citation below traces to a Dutch-language primary
source that was actually fetched and read this iteration (verbatim
Dutch quoted, with an English gloss alongside -- the Dutch is primary,
the English is this repo's own translation).

## Regulatory catalog (verified facts only)

Every fact in `src/marketentry/facts.cljk` traces to an official/
primary source fetched and read directly this session. See that
namespace's docstring for the full source list. **Headline finding,
verified rather than assumed**: Suriname has **no dedicated
'aanbestedingswet' (procurement act)** and **no dedicated procurement-
regulator agency** -- confirmed by exhaustively reviewing De Nationale
Assemblee's (`dna.sr`) own complete law index (~140 titles across both
its 'Geldende teksten t/m 2005' and 'Wetten na 2005' categories); none
is titled with 'aanbesteding'. Instead:

- **Public procurement** is governed directly by the
  **Comptabiliteitswet 1952** ('WET van 2 december 1952, regelende de
  wijze van beheer en verantwoording van de geldmiddelen in Suriname',
  G.B. 1952 no. 111) -- its own Artikel 18 mandates public tendering
  ('in het openbaar aanbesteed') of works/supplies/transport for the
  State, deviation only by presidential decision with recorded reasons
  + a copy sent to Parliament -- and its implementing decree, the
  **Comptabiliteitsbesluit** (G.B. 1953 no. 100, amended through S.B.
  2004 no. 116), whose own Artikel 18 adds two independently-
  recomputable numeric thresholds: contracts above **SRD 30,000**
  require an openbare aanbesteding (public tender) unless the President
  grants an on-the-record exception; contracts above **SRD 4,000** and
  up to SRD 30,000 may be procured without tender but require **Raad
  van Ministers** (Council of Ministers) approval; SRD 4,000 or below
  needs neither. There is no dedicated procurement regulator --
  procurement authority is decentralized 'door of vanwege de betrokken
  minister' (by or on behalf of the responsible minister) per
  department, with cross-departmental procurements held by or on
  behalf of the Minister of Finance.
- **Business/company registration**: the **Kamer van Koophandel en
  Fabrieken (KKF)** maintains the public Handelsregister (Trade
  Register), grounded in the Handelsregisterwet 1936 (G.B. 1936 no.
  149, geldende tekst G.B. 1962 no. 187, laatstelijk gewijzigd bij S.B.
  1981 no. 23). KKF's OWN separate institutional legal basis, the Wet
  Kamers van Koophandel 1962 (G.B. 1962 no. 85), is confirmed via a
  1997 amending law's own explanatory memorandum (Nota van Toelichting).
  Registration runs through KKF's own e-services portal (Naamgoedkeuring
  / name approval precedes Handelsregister entry; extract cost SRD
  300).
- **Tax registration**: the **Belastingdienst Suriname**
  (belastingdienst.sr) operates a registration/filing portal
  (`portaal.belastingdienst.sr/OPO`) covering BTW (VAT), Loonbelasting
  (wage tax), Premie AOV (old-age-provision premium), and
  Inkomstenbelasting (income tax). The three underlying tax laws --
  Inkomstenbelasting 1922 (G.B. 1921 no. 122), Wet Loonbelasting (S.B.
  1981 no. 181), Wet Omzetbelasting 1997 (S.B. 1997 no. 87) -- are
  confirmed via their own citations inside the Investeringswet 2001
  text. **No specific unified taxpayer-ID/FIN scheme name** was
  independently confirmed -- this is an honest gap, not a fabricated
  acronym.
- **Foreign/general investment**: the **Investeringswet 2001** ('WET
  van 3 juni 2002', S.B. 2002 no. 42) establishes fiscal/non-fiscal
  facilities for investment in agriculture, animal husbandry,
  fisheries, aquaculture, mining, forestry and tourism (excluding
  casino operation). Requests are filed with **Investsur** (Instituut
  ter bevordering van investeringen), itself confirmed real and
  currently-legislated by the law's own Artikel 1(1)(b) (established
  by a companion law of the same date, S.B. 2002 no. 41). A live
  Investsur website was NOT independently reached this session (a
  guessed domain, `investsur.sr`, timed out) -- its existence/legal
  basis rests on the primary law text, not a self-asserted site claim.

See `src/marketentry/facts.cljk`'s namespace docstring for the "explicitly
NOT claimed" section listing every fabrication trap deliberately avoided
(no invented procurement-regulator acronym, no invented e-procurement
portal, no invented tax-ID scheme name, no invented local-content law).

## Implementation (R0)

| Piece | Location |
|---|---|
| Actor namespaces | `src/marketentry/*` |
| Governor | `:market-entry-compliance-governor` |
| Ops | `:engagement/intake` · `:jurisdiction/assess` · `:filing/draft` · `:filing/submit` |
| Flagship HARD check | `procurement-threshold-noncompliant` (Comptabiliteitsbesluit Artikel 18's SRD 4,000/SRD 30,000 three-tier threshold recompute -- genuinely different SHAPE from every sibling actor's own flagship, a numeric tier recompute rather than a trigger-set or boolean flag; see `docs/adr/0001-architecture.md`) |
| Other checks | `evidence-incomplete` · `engagement-fee-mismatch` · `tax-registration-unconfirmed` (Belastingdienst, unconditional) |
| Compliance catalog | `src/statute/facts.cljk` -- Handelsregisterwet 1936, Wet Kamers van Koophandel 1962, Comptabiliteitswet 1952, Comptabiliteitsbesluit, Investeringswet 2001, Inkomstenbelasting 1922, Wet Loonbelasting, Wet Omzetbelasting 1997, Arbeidswet 1963 |
| Tests | `clojure -M:dev:test` |
| Demo | `clojure -M:dev:run` |
| Architecture ADR | [`docs/adr/0001-architecture.md`](docs/adr/0001-architecture.md) |

`:filing/submit` is never in any phase's `:auto` set -- human sign-off
is structural, not a rollout milestone.

## Actuation: `filing/draft` and `filing/submit` are always human-gated

`marketentry.registry/register-draft` and `register-submit` build an
**unsigned, non-authoritative record** of what a market-entry operator
*intends* to file -- neither function, nor anything in this actor,
ever calls a real Surinamese government system. Whether that record is
ever turned into a real filing submission is entirely the human
market-entry operator's decision, enforced by two independent layers
that must BOTH agree before a real-world act happens:

1. **`marketentry.governor/high-stakes`** marks
   `:actuation/draft-filing` and `:actuation/submit-filing` as
   high-stakes -- any proposal carrying either `:stake` value always
   `:escalate?`s, regardless of confidence or how clean the governor's
   other checks are.
2. **`marketentry.phase/phases`** never puts `:filing/draft` or
   `:filing/submit` in any phase's `:auto` set (see the explicit
   comment in `phase.cljc`: "a permanent structural fact, not a rollout
   milestone still to come").

Concretely, in `marketentry.operation/build`'s StateGraph,
`interrupt-before #{:request-approval}` means the graph run itself
*pauses* (checkpointed, resumable) the moment a `:filing/draft` or
`:filing/submit` proposal reaches that node -- there is no code path
that reaches `:commit` for either op without a human explicitly
resuming the run with `{:approval {:status :approved :by "<human>"}}`.
`test/marketentry/governor_contract_test.cljk`'s
`filing-draft-and-submit-never-auto-commit` test asserts this
end-to-end, at phase 3 (the most permissive phase).

## No robotics premise -- digital/data service exemption

Market-entry and procurement-compliance navigation is a pure data/software
service with no physical-domain work (filing preparation, document
checklists, regulatory-change monitoring) -- the same exemption class as
`cloud-itonami-6310` (HR SaaS replacement) and `cloud-itonami-gtin-*`.
`blueprint.edn` sets `:itonami.blueprint/robotics false` and
`:required-technologies` lists only real capabilities (`:identity`,
`:forms`, `:dmn`, `:bpmn`, `:audit-ledger`), no `:robotics`.

## Core Contract

```text
operator intake + prior filing history
        |
        v
Compliance Advisor -> Market-Entry Compliance Governor -> filing draft, or human sign-off
        |
        v
gated filing submission + audit ledger
```

No automated proposal can submit a filing the governor refuses,
suppress a compliance record, or claim a legal/tax conclusion the
governor has not cleared. `:filing/submit` is never in any phase's
`:auto` set -- it always requires human sign-off.

## What this is NOT

- **Not the government of Suriname.** This blueprint is an independent
  operator the government contracts with or that bids into its
  procurement -- never the government itself, and never an official
  channel.
- **Not legal or tax advice.** Every regulatory claim must cite the
  official source and route final filings to Suriname-licensed counsel
  or a registered agent where the law requires licensed
  representation.
- **Not a claim that Suriname has a dedicated procurement regulator or
  e-procurement portal.** This iteration verified, via De Nationale
  Assemblee's own complete law index, that neither exists -- procurement
  is instead grounded in the general Comptabiliteitswet/-besluit. Do
  not "upgrade" this catalog with an invented agency name or portal.

## Capability layer

Required capabilities (`blueprint.edn`):

- :identity
- :forms
- :dmn
- :bpmn
- :audit-ledger

See [`docs/business-model.md`](docs/business-model.md) and
[`docs/operator-guide.md`](docs/operator-guide.md).

## License

AGPL-3.0-or-later.

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for Suriname:

- `src/culture/facts.cljk` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one. **This
catalog was NOT touched by this iteration's market-entry/statute work.**
