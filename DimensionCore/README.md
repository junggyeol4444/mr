# DimensionCore

핵심 차원 관리 플러그인입니다.

## 기능
- 3대 차원(판타지/무협/지구) 및 상·하위 차원(마계, 천계, 선계, 요계, 정령계, 명계, 탑) 관리
- 차원 균형 에너지 시스템 (0~100 실시간 변동)
  - 30 이하: 침식 경고 및 이벤트 트리거
  - 71 이상: 과충전 경고
- 차원 간 이동 포탈 시스템
- 다른 플러그인이 사용할 공통 API(`DimensionAPI`) 제공

## 명령어
| 명령어 | 설명 |
|--------|------|
| `/worldswitch <차원>` | 차원 이동 (튜토리얼 중 1회 한정) |

## 차원 목록
| ID | 한국어 이름 |
|----|-----------|
| `fantasy` | 판타지 |
| `martial` | 무협 |
| `earth` | 지구 |
| `underworld` | 마계 |
| `heaven` | 천계 |
| `immortal` | 선계 |
| `demon` | 요계 |
| `spirit` | 정령계 |
| `netherworld` | 명계 |
| `tower` | 탑 |

## API 사용법
```java
// 다른 플러그인에서 DimensionAPI 사용
DimensionAPI api = DimensionAPI.get();

// 플레이어 현재 차원 조회
Dimension dim = api.getPlayerDimension(player);

// 차원 균형 조정
api.adjustDimensionBalance(Dimension.FANTASY, -5.0);

// 침식/과충전 여부 확인
boolean eroded = api.isErosion(Dimension.FANTASY);
```

## 설정 (config.yml)
| 항목 | 기본값 | 설명 |
|------|--------|------|
| `balance.erosion_threshold` | 30 | 침식 임계값 |
| `balance.overcharge_threshold` | 71 | 과충전 임계값 |
| `balance.default` | 50 | 기본 균형값 |
| `balance.tick_interval` | 200 | 균형 업데이트 주기 (틱) |
| `portals.enabled` | true | 포탈 활성화 여부 |
| `portals.cooldown` | 60 | 포탈 쿨다운 (초) |
