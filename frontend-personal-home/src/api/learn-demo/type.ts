export interface SummaryItem {
  iconPath: string;
  title: string;
  score: number;
  maxScore: number;
  tone: 'reaction' | 'memory' | 'verbal' | 'visual';
}
