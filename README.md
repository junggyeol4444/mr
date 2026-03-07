# MR — 다차원 MMORPG 마인크래프트 서버 플러그인 모음

> **Paper 1.20.4** 기반의 다차원 세계관을 가진 MMORPG 마인크래프트 서버 플러그인 프레임워크입니다.

---

## 📖 이 서버는 뭐하는 서버인가요?

**MR**은 마인크래프트 위에서 동작하는 **본격 MMORPG 서버**를 만들기 위한 **14개의 모듈형 플러그인** 모음입니다.

### 🌍 세계관

- **3개의 메인 차원**: 판타지(Fantasy), 무협(Martial), 지구(Earth)
- **7개의 서브 차원**: 마계, 천계, 선계, 요계, 정령계, 명계, 탑
- **엔드게임 목표**: 3개의 차원 아티팩트를 수집하여 **심연(Abyss)** 을 개방

### 핵심 특징

| 기능 | 설명 |
|------|------|
| 🏷️ 종족 시스템 | 탄생 차원에 따라 7개 종족 중 하나로 결정 (악마, 천사, 선인, 요괴, 혼령체, 정령인, 인간) |
| ⚔️ 직업 시스템 | 판타지 17개 직업, 무협 5계열 무공, 지구 헌터 랭크(E~SSS) + 9개 각성 능력 |
| 📊 스탯 시스템 | 8개 기본 스탯 + 8개 차원 전용 스탯, 최대 레벨 999 |
| 💰 경제 시스템 | 차원별 화폐 (판타지: 동전~백금화, 무협: 동량~금량, 지구: 코인, 명계: 혼) |
| 🏰 길드 & 전쟁 | 최대 50인 길드, 영토 점령, 세금 징수, PVP 리그 |
| 📜 퀘스트 엔진 | 개인/자유/직업/메인/게이트 5종 퀘스트 |
| 🗼 100층 탑 | 10층 단위 테마별 던전, 50층 이상 시 동기화 게이트 발동 |
| 💀 죽음 & 부활 | 사망 시 명계 이동, 4가지 부활 방법 |
| 🧬 종족 진화 | 비가역적 종족 변환 및 초월(Transcendence) 시스템 |
| 🌐 월드 이벤트 | 차원 균형 에너지에 따른 서버 전체 이벤트 |
| 🤖 NPC 엔진 | 호감도 기반 AI NPC, NPC 간 정치·전쟁·교역 |
| 📚 스토리 & 결말 | 3개 아티팩트 수집 → 심연 개방 → 진영 선택 → 서버 엔딩 |

---

## 🛠️ 필요 환경 & 필수 조건

### 서버 요구사항

| 항목 | 요구사항 |
|------|----------|
| **마인크래프트 버전** | 1.20.4 |
| **서버 플랫폼** | [Paper](https://papermc.io/) (권장) 또는 Paper 호환 포크 (Purpur 등) |
| **Java 버전** | Java 17 이상 |
| **빌드 도구** | Gradle (포함되어 있음) |

### 외부 플러그인 의존성

이 프로젝트는 **외부 플러그인 의존성이 없습니다.** 14개 모듈이 모두 자체적으로 구현되어 있으며, Paper API만 사용합니다. 단, 서버 운영 시 아래 플러그인을 **추가로 설치하면 좋습니다**:

| 추가 권장 플러그인 | 용도 | 필수 여부 |
|---------------------|------|-----------|
| [LuckPerms](https://luckperms.net/) | 권한 관리 (명령어 퍼미션 세분화) | 선택 |
| [WorldGuard](https://enginehub.org/worldguard) | 월드 보호 및 영역 설정 | 선택 |
| [Vault](https://github.com/MilkBowl/Vault) | 타 경제 플러그인과의 호환성 | 선택 |
| [ProtocolLib](https://github.com/dmulloy2/ProtocolLib) | 패킷 기반 NPC 기능 확장 | 선택 |

---

## 📦 설치 방법

### 1. 빌드하기

```bash
# 레포지토리 클론
git clone https://github.com/junggyeol4444/mr.git
cd mr

# 전체 빌드 (14개 모듈 한 번에)
./gradlew build
```

빌드 완료 후 각 모듈 폴더의 `build/libs/` 디렉토리에 `.jar` 파일이 생성됩니다.

### 2. 플러그인 설치

```bash
# 빌드된 jar 파일들을 서버 plugins 폴더에 복사
cp DimensionCore/build/libs/DimensionCore-1.0.0.jar   /서버경로/plugins/
cp BirthSystem/build/libs/BirthSystem-1.0.0.jar       /서버경로/plugins/
cp StatSystem/build/libs/StatSystem-1.0.0.jar          /서버경로/plugins/
cp JobClassSystem/build/libs/JobClassSystem-1.0.0.jar  /서버경로/plugins/
cp QuestEngine/build/libs/QuestEngine-1.0.0.jar        /서버경로/plugins/
cp EconomyBridge/build/libs/EconomyBridge-1.0.0.jar    /서버경로/plugins/
cp DeathRealm/build/libs/DeathRealm-1.0.0.jar          /서버경로/plugins/
cp WorldEvents/build/libs/WorldEvents-1.0.0.jar        /서버경로/plugins/
cp NPCEngine/build/libs/NPCEngine-1.0.0.jar            /서버경로/plugins/
cp TowerGate/build/libs/TowerGate-1.0.0.jar            /서버경로/plugins/
cp GuildFaction/build/libs/GuildFaction-1.0.0.jar       /서버경로/plugins/
cp RaceEvolution/build/libs/RaceEvolution-1.0.0.jar    /서버경로/plugins/
cp TutorialManager/build/libs/TutorialManager-1.0.0.jar /서버경로/plugins/
cp LoreMyth/build/libs/LoreMyth-1.0.0.jar              /서버경로/plugins/
```

### 3. 서버 시작

```bash
java -Xmx4G -jar paper-1.20.4.jar
```

> ⚠️ **모든 14개 플러그인을 함께 설치해야 합니다.** 모듈 간 의존성이 있어 일부만 설치하면 오류가 발생할 수 있습니다.

---

## 🎮 사용법 — 플레이어 가이드

### 첫 접속 흐름

1. **서버 접속** → TutorialManager가 자동으로 튜토리얼 시작
2. **차원 배정** → 랜덤으로 3개 메인 차원 중 하나에 배정
3. **종족 결정** → 배정된 차원에 따라 종족이 자동 결정
4. **튜토리얼 6단계 진행**:
   - 차원 배정 → 전투 → 퀘스트 → 거래 → 부활 → 완료
5. **튜토리얼 완료 후** 메인 서버 진입

> 💡 튜토리얼 중 `/worldswitch <차원>` 명령어로 **1회에 한해** 다른 차원으로 변경할 수 있습니다.

### 주요 명령어

| 명령어 | 설명 | 플러그인 |
|--------|------|----------|
| `/stats [플레이어]` | 스탯 확인 | StatSystem |
| `/statpoint <스탯> <포인트>` | 스탯 포인트 배분 | StatSystem |
| `/job info` | 직업 정보 확인 | JobClassSystem |
| `/job select <직업>` | 직업 선택 | JobClassSystem |
| `/quest list` | 퀘스트 목록 | QuestEngine |
| `/quest accept <퀘스트>` | 퀘스트 수락 | QuestEngine |
| `/quest info <퀘스트>` | 퀘스트 상세 정보 | QuestEngine |
| `/quest abandon <퀘스트>` | 퀘스트 포기 | QuestEngine |
| `/money balance` | 잔액 확인 | EconomyBridge |
| `/money pay <플레이어> <금액>` | 송금 | EconomyBridge |
| `/money exchange` | 차원 간 환전 | EconomyBridge |
| `/soulbank deposit\|withdraw\|balance` | 혼 은행 (명계 화폐) | EconomyBridge / DeathRealm |
| `/resurrect` | 부활 | DeathRealm |
| `/tower info` | 탑 정보 | TowerGate |
| `/tower floor` | 현재 층 확인 | TowerGate |
| `/tower rank` | 탑 랭킹 | TowerGate |
| `/guild create <이름>` | 길드 생성 | GuildFaction |
| `/guild info` | 길드 정보 | GuildFaction |
| `/guild invite <플레이어>` | 길드 초대 | GuildFaction |
| `/guild leave` | 길드 탈퇴 | GuildFaction |
| `/guild disband` | 길드 해산 (리더 전용) | GuildFaction |
| `/evolve <경로>` | 종족 진화 | RaceEvolution |
| `/evolve confirm` | 진화 확정 (비가역적!) | RaceEvolution |
| `/transcend` | 초월 (스탯 한계 돌파) | RaceEvolution |
| `/worldswitch <차원>` | 차원 이동 (튜토리얼 중 1회) | DimensionCore |

---

## 🏗️ 모듈 상세 설명

### 의존성 구조

```
DimensionCore (핵심 - 독립)
├── BirthSystem
├── StatSystem
│   └── JobClassSystem (StatSystem 의존)
├── QuestEngine
├── EconomyBridge
│   ├── GuildFaction (EconomyBridge 의존)
│   └── DeathRealm (EconomyBridge + BirthSystem 의존)
├── WorldEvents
├── NPCEngine
├── TowerGate
├── LoreMyth
├── TutorialManager (BirthSystem 의존)
└── RaceEvolution (BirthSystem + StatSystem 의존)
```

### 각 모듈 요약

#### 🌐 DimensionCore — 차원 관리 핵심 모듈
모든 플러그인의 기반. 10개 차원 상태 관리, 차원 균형 에너지 시스템(0~100), 포탈 시스템, API 서비스를 제공합니다.
- 균형 에너지 ≤30: **침식 상태** (디버프, 악마 스폰)
- 균형 에너지 ≥71: **과충전 상태** (버프, 월드 보스)

#### 🐣 BirthSystem — 탄생 & 종족 결정
첫 접속 시 랜덤 차원 배정 및 종족 자동 결정. 7개 종족마다 고유 스탯 보너스와 성장 배율이 다릅니다.

| 종족 | 탄생 차원 | 특수 능력 | 성장 배율 |
|------|-----------|-----------|-----------|
| 악마 | 마계 | STR+3, 마기+5 | ×1.5 |
| 천사 | 천계 | WIS+3, 천기+5 | ×1.5 |
| 선인 | 선계 | 전 스탯+2 | ×2.0 |
| 요괴 | 요계 | DEX+3, 요기+5 | ×1.5 |
| 혼령체 | 명계 | INT+3, 혼기+5 (사망 시 아이템 미드롭) | ×1.5 |
| 정령인 | 정령계 | INT+2, 정령마나+5 | ×1.5 |
| 인간 | 판타지/무협/지구 | 없음 | ×1.0 |

#### 📊 StatSystem — 레벨 & 스탯
경험치, 레벨링, 스탯 배분 시스템. 레벨당 3포인트, 최대 레벨 999.

#### ⚔️ JobClassSystem — 직업 & 스킬
차원별로 완전히 다른 직업 체계:
- **판타지**: 검사, 팔라딘, 마법사 등 17개 직업
- **무협**: 외공/내공/경공/심법/경기 5계열, 하나를 배우면 나머지 습득 확률 감소
- **지구**: E~SSS 헌터 랭크 + 그림자/염동력/시간왜곡 등 9가지 각성

#### 📜 QuestEngine — 퀘스트
5종 퀘스트 (개인/자유/직업/메인/게이트). 킬, 차원 도달 등 다양한 목표 유형과 보상 시스템.

#### 💰 EconomyBridge — 경제
차원별 화폐 시스템 + 교환소 + 혼 은행(이자율 1%, 최대 예치 1000만).

#### 💀 DeathRealm — 죽음 & 부활
사망 시 명계로 이동. 부활 방법 4가지: ①부활 퀘스트 완료 ②혼 지불(5만) ③혼 은행 선결제 ④타인 대납.

#### 🌪️ WorldEvents — 월드 이벤트
차원 균형 에너지에 따른 서버 전체 이벤트 (마계 침식 주간, 천계 축복, 요계 개방, 동기화 게이트).

#### 🤖 NPCEngine — AI NPC
호감도(-100~+100) 기반 NPC. ≥50 동맹(특수 퀘스트 해금), ≤-50 적대. NPC 간 정치·교역 시스템.

#### 🗼 TowerGate — 100층 탑
10층 단위 테마 (생존/퍼즐/환경/혼합/국가협력/심층생존/고급퍼즐/극한환경/탑보스/심연게이트). 50층 이상 도달자 50명 시 동기화 게이트 발동.

#### 🏰 GuildFaction — 길드 & 전쟁
최대 50인 길드, 4단계 직급 (멤버/간부/부길마/길드마). 영토 점령 + 세금 징수(1~20%) + PVP 리그.

#### 🧬 RaceEvolution — 종족 진화
비가역적 종족 변환 (정령인/반마족/기계인/타락 등). 초월 시스템으로 스탯 한계(9999) 돌파. 최대 초월 3단계.

#### 📖 TutorialManager — 튜토리얼
첫 접속 시 자동 시작되는 6단계 가이드. 완료 후 메인 서버 진입.

#### 📚 LoreMyth — 스토리 & 엔딩
3개 차원 아티팩트 (루멘의 불씨/태극 코어/프로토스 큐브) 수집 → 심연 개방 → 진영 선택 (수호자/타락자/초월자) → 서버 엔딩.

---

## 📁 프로젝트 구조

```
mr/
├── build.gradle              # 루트 빌드 설정 (Paper 1.20.4, Java 17)
├── settings.gradle           # 14개 모듈 포함
├── gradle/                   # Gradle Wrapper
│
├── DimensionCore/            # 핵심 차원 관리
├── BirthSystem/              # 탄생 & 종족
├── StatSystem/               # 레벨 & 스탯
├── JobClassSystem/           # 직업 & 스킬
├── QuestEngine/              # 퀘스트
├── EconomyBridge/            # 경제
├── DeathRealm/               # 죽음 & 부활
├── WorldEvents/              # 월드 이벤트
├── NPCEngine/                # AI NPC
├── TowerGate/                # 100층 탑
├── GuildFaction/             # 길드 & 전쟁
├── RaceEvolution/            # 종족 진화
├── TutorialManager/          # 튜토리얼
└── LoreMyth/                 # 스토리 & 엔딩
```

각 모듈의 내부 구조:

```
ModuleName/
├── src/main/java/com/mr/{package}/
│   ├── {Module}Plugin.java   # 메인 플러그인 클래스
│   ├── managers/             # 상태 관리
│   ├── commands/             # 명령어 처리
│   ├── listeners/            # 이벤트 리스너
│   ├── models/               # 데이터 모델
│   ├── api/                  # 공개 API (일부 모듈)
│   └── utils/                # 유틸리티
├── src/main/resources/
│   ├── plugin.yml            # 플러그인 메타데이터
│   └── config.yml            # 설정 파일
└── build.gradle              # 모듈 빌드 설정
```

---

## ⚙️ 설정 & 커스터마이징

서버 시작 후 각 플러그인 폴더에 `config.yml`이 자동 생성됩니다. 주요 설정:

| 설정 항목 | 파일 위치 | 설명 |
|-----------|-----------|------|
| 차원 균형 임계값 | `DimensionCore/config.yml` | 침식/과충전 기준값 (기본: 30/71) |
| 포탈 쿨다운 | `DimensionCore/config.yml` | 차원 이동 쿨타임 |
| 레벨당 스탯 포인트 | `StatSystem/config.yml` | 기본: 3 |
| 최대 레벨 | `StatSystem/config.yml` | 기본: 999 |
| 부활 비용 | `DeathRealm/config.yml` | 혼 지불 비용 (기본: 50,000) |
| 길드 최대 인원 | `GuildFaction/config.yml` | 기본: 50 |
| 세금 범위 | `GuildFaction/config.yml` | 기본: 1~20% |
| 혼 은행 이자율 | `EconomyBridge/config.yml` | 기본: 1% |
| 탑 층수 | `TowerGate/config.yml` | 기본: 100 |
| 초월 최대 단계 | `RaceEvolution/config.yml` | 기본: 3 |

---

## 🔧 개발자 정보

- **그룹 ID**: `com.mr`
- **버전**: `1.0.0`
- **빌드**: Gradle 멀티프로젝트
- **API**: Paper API 1.20.4
- **Java**: 17+

### 개발 환경 세팅

```bash
git clone https://github.com/junggyeol4444/mr.git
cd mr
./gradlew build
```

### API 사용 예시

DimensionCore는 Bukkit 서비스로 `DimensionAPI`를 등록합니다. 다른 플러그인에서 사용 가능:

```java
// DimensionAPI 가져오기
DimensionAPI api = getServer().getServicesManager()
    .getRegistration(DimensionAPI.class).getProvider();

// 플레이어의 현재 차원 확인
String dimension = api.getPlayerDimension(player);

// 차원 균형 에너지 확인
double balance = api.getBalanceEnergy("fantasy");
```

---

## 📝 라이선스

이 프로젝트의 라이선스 정보는 별도로 명시되어 있지 않습니다.