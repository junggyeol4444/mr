# StatSystem

스탯 & 성장 플러그인입니다.

## 기능
- 기본 스탯: STR(근력), DEX(민첩), INT(지능), VIT(체력), WIS(지혜), CHA(매력), LUK(행운), RES(저항)
- 특수 스탯: 마기, 천기, 선기, 요기, 혼기, 어둠 마나, 정령 마나, 진기, 룬 슬롯, 정령 슬롯
- 경험치 획득 및 레벨업 시스템
- 레벨업 시 스탯 포인트 지급 (기본 3포인트/레벨)
- 최대 레벨: 999

## 명령어
| 명령어 | 설명 |
|--------|------|
| `/stats [플레이어]` | 스탯 조회 |
| `/statpoint <스탯> <포인트>` | 스탯 포인트 투자 |

## 의존성
- DimensionCore

## 설정 (config.yml)
- `stats.base`: 기본 스탯값
- `leveling.max_level`: 최대 레벨 (기본: 999)
- `leveling.exp_per_level_multiplier`: 레벨당 필요 경험치 계수
- `leveling.stat_points_per_level`: 레벨당 스탯 포인트 (기본: 3)
