# ADR-0001: Architecture — Suriname market-entry compliance actor (`marketentry`)

**Status**: accepted
**Date**: 2026-07-23

## Context

`cloud-itonami-iso3166-sur` was published carrying ONLY `src/culture/`
(a regional-culture catalog) -- its `:public-sector/market-entry-
compliance` domain, declared in `blueprint.edn`, was unimplemented (no
`src/marketentry`/`src/statute` at all, and `docs/business-model.md`/
`docs/operator-guide.md` were one-line stubs). This ADR closes that
gap, following the pattern established by `cloud-itonami-iso3166-jpn`
(origin) and studied directly from three sibling repos this session:
`cloud-itonami-iso3166-guy` (Guyana, structural model), `-hti` (Haiti),
`-gnb` (Guinea-Bissau, honest-thin-catalog model).

`CONTRIBUTING.md` and `GOVERNANCE.md` in this repo, before this change,
carried leftover template references to "Cambodia"/"Cambodian" from
whatever sibling scaffold they were copied from -- both were fixed as
part of this change.

## Decision

Build the full governed-actor architecture for `marketentry`, mirroring
the family's harness verbatim (StateGraph node names, governor
hard/escalate contract, phase 0-3 rollout, `Store` protocol with
MemStore + DatomicStore parity via `kotoba-lang/langchain-store`) with
Suriname's own real market-entry rules for the country-specific
content -- researched this session via direct fetches of Dutch-language
official government sources (`dna.sr`, `belastingdienst.sr`,
`surinamechamber.com`/`kkf.sr`, `gov.sr`), never from training-data
memory.

- **Store**: `marketentry.store`, MemStore + DatomicStore, proven parity
  via contract test. DatomicStore uses `langchain-store.core`
  (`ls/enc`/`ls/dec*`/`ls/read-stream`/`ls/append-blob!`).
- **Registry**: `marketentry.registry`, pure DRAFT-certificate
  construction via `unsigned-certificate`, jurisdiction-scoped sequence
  numbering (`SUR-DFT-000000`, `SUR-SUB-000000`), plus the flagship
  procurement-threshold recompute (see below).
- **Governor**: `:market-entry-compliance-governor` (family keyword from
  `blueprint.edn`).
- **Entity shape**: `engagement`, sequential draft -> submit on the same
  record. `high-stakes` = `#{:actuation/draft-filing
  :actuation/submit-filing}`.
- **Phase**: 0->3; `:filing/draft` and `:filing/submit` NEVER auto-
  commit at any phase.

## Research finding: Suriname has no dedicated procurement act or regulator

This is the single most important research finding of this iteration,
and it directly shaped the flagship check design. De Nationale
Assemblee (`dna.sr`, Suriname's parliament) publishes a COMPLETE,
browsable index of enacted law at `/wetgeving/surinaamse-wetten/`, in
two categories: "Geldende teksten t/m 2005" (~140 titles, every one
listed and reviewed this session) and "Wetten na 2005" (post-2005
enactments, also reviewed in full). **Neither category contains any
law titled with "aanbesteding"** (procurement/tendering). This was
verified by an exhaustive category listing, not assumed from the
absence of a search result.

Instead, government procurement of works/supplies/transport is governed
by Suriname's GENERAL public-finance law: the **Comptabiliteitswet
1952** ("WET van 2 december 1952, regelende de wijze van beheer en
verantwoording van de geldmiddelen in Suriname", G.B. 1952 no. 111,
amended G.B. 1955 no. 38 / G.B. 1969 no. 58 / S.B. 2003 no. 76). Its
own Artikel 18, fetched and read directly (PDF text-layer, via the Read
tool, not paraphrased), reads verbatim: "Het uitvoeren van werken en
het doen van leverantien en transporten ten behoeve van de Staat worden
in het openbaar aanbesteed. Alleen wanneer de President zulks in het
belang van de Staat nodig oordeelt, kan van die regel worden
afgeweken." The implementing decree, the **Comptabiliteitsbesluit**
(G.B. 1953 no. 100, amended through S.B. 2004 no. 116), restates this
in its own Artikel 18 and adds the numeric thresholds this catalog's
flagship check is built on (see below). There is no dedicated
procurement-regulator agency -- procurement is decentralized "door of
vanwege de betrokken minister" (by or on behalf of the responsible
minister) per department, with cross-departmental procurements held by
or on behalf of the Minister of Finance.

This honestly rules out modeling Suriname's flagship check the way
Guyana's (NPTA-administered Register of Bidders) or Guinea-Bissau's
(ARCP-administered Código dos Contratos Públicos) are modeled -- there
is no comparable dedicated regulator or registration regime to check
against. The flagship check for Suriname is therefore built on the
Comptabiliteitsbesluit's own numeric thresholds instead (see below), a
genuinely different SHAPE from every sibling's own flagship, per this
task's own instruction not to force a borrowed shape onto a different
underlying mechanism.

### New flagship HARD check: `procurement-threshold-noncompliant` -- the Comptabiliteitsbesluit Artikel 18 three-tier SRD threshold

The Comptabiliteitsbesluit's own Artikel 18 (as amended, fetched and
read directly from `https://www.dna.sr/media/hexjjg1r/comptabiliteitsbesluit.pdf`)
lists two exceptions to the default public-tender rule, the second
reading verbatim: "voor leveringen, werkzaamheden en transporten tot
geen hoger bedrag dan SRD.30.000,-- (DERTIGDUIZEND SURINAAMSE DOLLAR),
met dien verstande dat de goedkeuring van de Raad van Ministers vereist
is voor uitgaven een bedrag van SRD. 4.000,- (VIERDUIZEND SURINAAMSE
DOLLAR) te boven gaande."

This is a genuine, independently-recomputable THREE-TIER rule:

1. Contract value <= SRD 4,000: neither a public tender nor Council-of-
   Ministers approval is required.
2. Contract value > SRD 4,000 and <= SRD 30,000: no tender required,
   but Raad van Ministers (Council of Ministers) approval IS required.
3. Contract value > SRD 30,000: an openbare aanbesteding (public
   tender) is required, UNLESS the President has granted an
   on-the-record exception (with recorded reasons, copied to
   Parliament, per the Comptabiliteitswet's own Artikel 18).

`marketentry.registry/procurement-threshold-noncompliant?`
independently recomputes this from the engagement's own
`:contract-value-srd`, `:procurement-path`,
`:presidential-exception?` and `:council-of-ministers-approved?`
fields -- `marketentry.store/demo-data`'s `eng-4` (top tier, no
exception -> noncompliant), `eng-6` (middle tier, no approval ->
noncompliant), `eng-7` (middle tier, approval on file -> NOT
noncompliant), `eng-8` (top tier, presidential exception on file ->
NOT noncompliant), and `eng-9` (top tier, properly tendered -> NOT
noncompliant) prove all three tiers in both directions, mirrored by
`test/marketentry/registry_test.cljk` and
`test/marketentry/governor_contract_test.cljk`.

### Other HARD checks (all unoverridable)

1. **spec-basis** -- never invent a jurisdiction's market-entry
   requirements (`marketentry.facts` catalog).
2. **evidence-incomplete** -- draft/submit require a full assessment
   checklist on file (KKF Handelsregister record, Belastingdienst tax
   registration record, contract-value/procurement-path classification
   record, authorized-representative record).
3. **procurement-threshold-noncompliant** -- see above (FLAGSHIP).
4. **engagement-fee-mismatch** -- recompute `base-fee + monthly-rate ×
   monitoring-months` (ground-truth-recompute discipline, generic
   across the fleet).
5. **tax-registration-unconfirmed** -- UNCONDITIONAL, grounded in the
   general Belastingdienst registration baseline (no Suriname-specific
   "government contractors must have a tax ID" clause was
   independently found, unlike Guyana's GRA guidance -- this check is
   honestly scoped to the general tax-registration requirement, not a
   fabricated procurement-specific mandate).
6. **already-drafted / already-submitted** -- dedicated booleans, never
   a `:status` value.

### Business registration folded into evidence-incomplete, not a separate conditional check

Guyana's flagship check (`business-registration-missing`) is grounded
in a documented FIVE-WAY non-resident "carrying on an undertaking"
trigger set from the Companies Act 1991. No comparable documented
conditionality regime was found for Suriname's KKF/Handelsregister
requirement this session -- inventing one to mirror Guyana's shape
would itself be a fabrication. Business/KKF registration is instead
folded into the universal `evidence-incomplete` checklist (a required-
evidence item, not a standalone conditional HARD check), the same
honest-scope-narrowing discipline `cloud-itonami-iso3166-gnb` applies
when a genuinely-real regime doesn't clear the bar for a dedicated
check slot.

### What was NOT touched

- `src/culture/facts.cljk` (and `schema/culture.edn` /
  `data/culture-tx.edn`) is completely untouched.
- `CONTRIBUTING.md`/`GOVERNANCE.md` had their leftover "Cambodia"/
  "Cambodian" template references corrected to Suriname, but their
  structural/procedural content (unrelated to country-specific facts)
  is otherwise the fleet-standard text, unchanged.

## Consequences

- `marketentry.facts` states only independently-traceable facts, each
  attributable to a specific official Dutch-language source URL
  (`dna.sr`, `belastingdienst.sr`, `surinamechamber.com`), with an
  explicit "explicitly NOT claimed" section (no invented procurement-
  regulator acronym, no invented e-procurement portal, no invented
  tax-ID scheme name, no invented local-content law).
- The flagship check is a numeric three-tier threshold recompute -- a
  genuinely different check SHAPE from every other iso3166 sibling
  studied this session (Guyana's non-resident trigger set, Guinea-
  Bissau's boolean registration flag), grounded in a general
  public-finance decree rather than a dedicated procurement act,
  because that is honestly what exists for Suriname.
- Honest coverage gaps are disclosed rather than papered over: no
  confirmed unified taxpayer-ID scheme name, no confirmed local-content
  law, and Investsur's own live website was not independently reached
  this session (a guessed domain timed out) -- its existence rests on
  the Investeringswet 2001's own primary text, not a self-asserted site
  claim.
