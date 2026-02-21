# EconomyBridge

경제 시스템 플러그인입니다.

## 기능
- 차원별 통화 시스템
  - 판타지: 동화, 은화, 금화, 백금화
  - 무협: 동량, 은량, 금량
  - 지구: 코인
  - 명계: 소울
- 차원 간 환율 (명계에서만 환전 가능)
- 소울 뱅크 (예금/인출)
- 플레이어 간 송금

## 명령어
| 명령어 | 설명 |
|--------|------|
| `/money balance` | 잔액 조회 |
| `/money pay <플레이어> <금액> <통화>` | 송금 |
| `/money exchange <금액> <from> <to>` | 환전 |

## 통화 목록
| ID | 한국어 | 차원 |
|----|--------|------|
| `COPPER_COIN` | 동화 | 판타지 |
| `SILVER_COIN` | 은화 | 판타지 |
| `GOLD_COIN` | 금화 | 판타지 |
| `PLATINUM_COIN` | 백금화 | 판타지 |
| `COPPER_LIANG` | 동량 | 무협 |
| `SILVER_LIANG` | 은량 | 무협 |
| `GOLD_LIANG` | 금량 | 무협 |
| `COIN` | 코인 | 지구 |
| `SOUL` | 소울 | 명계 |

## 의존성
- DimensionCore
